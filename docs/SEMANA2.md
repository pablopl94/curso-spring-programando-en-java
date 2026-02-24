## SEMANA 2 — CICLO DE VIDA, INTERCEPTORES Y FILTROS
---

### Introducción

Se ha creado un endpoint `/checkout` en la ruta `api/order` para validar todo lo aprendido durante la semana:

- **Ciclo de vida del Bean** con `@PostConstruct` y `@PreDestroy`
- **Filtros** con validación del header `X-Transaction-Id`
- **Interceptores** con medición del tiempo de respuesta
- **Eventos asíncronos** con `@Async` y `@EventListener`

> Para ver todos los logs activa el perfil `dev` que ya lleva el nivel DEBUG configurado.
> Para ver cómo activar los perfiles → [Ver](#semana-1---comandos-para-ejecutar-la-aplicación)

---

### 2.1 Ciclo de vida de un bean

Spring gestiona el ciclo de vida de cada bean desde que arranca la aplicación hasta que se apaga. Nos enganchamos a ese ciclo con `@PostConstruct` y `@PreDestroy` para ejecutar lógica en el momento exacto que necesitamos.

Estos métodos están en `AuditablePaymentProcessor` (clase abstracta), así que todos los processors los heredan automáticamente sin repetir código.

#### @PostConstruct — Al arrancar

Se ejecuta una sola vez justo después de que Spring ha creado el bean e inyectado todas sus dependencias:

```java
@PostConstruct
private void init() {
    log.info("[LOG] Configurando procesador {}...", beanName);
}
```

Log esperado al arrancar la app:

```
INFO [main] MockProcessor : [LOG] Configurando procesador mockProcessor...
INFO [main] TomcatWebServer : Tomcat started on port 8081 (http)
INFO [main] Application : CodeJa levantado con éxito en el perfil: [dev]
INFO [main] Application : La pasarela de pago activa es: mock
```

#### @PreDestroy — Al apagarse

Se ejecuta justo antes de que Spring destruya el bean al apagar la aplicación:

```java
@PreDestroy
private void destroy() {
    log.info("[LOG] Cerrando conexiones de {} antes del apagado...", beanName);
}
```

Log esperado al parar la app:

```
INFO [main] MockProcessor : [LOG] Cerrando conexiones de mockProcessor antes del apagado...
```

---

### 2.2 Filtros

Los filtros actúan antes de que la petición llegue al controller. Son el primer punto de entrada y el último de salida.
Para practicar con los filtros, he implementado un `TransactionFilter` que valida el header `X-Transaction-Id` en cada petición.

#### Petición correcta — con header

```bash
curl -X POST http://localhost:8081/api/order/checkout \
  -H "Content-Type: application/json" \
  -H "X-Transaction-Id: TXN-001" \
  -d '{"amount": 100, "userEmail": "pablo@test.com"}'
```

Respuesta esperada `HTTP 200`:

```json
{
  "transactionId": "93955d68-9c9a-4cfa-b44f-159dcbc2d43e",
  "status": "ACCEPTED",
  "processedAt": "2026-02-24T20:52:40.996",
  "amount": 100.0
}
```

#### Petición incorrecta — sin header

Si mandamos la petición sin el header `X-Transaction-Id`, el filtro lo detecta y rechaza la petición antes de que llegue al controller:

```bash
curl -X POST http://localhost:8081/api/order/checkout \
  -H "Content-Type: application/json" \
  -d '{"amount": 100, "userEmail": "pablo@test.com"}'
```

Respuesta esperada `HTTP 400`:

```json
{
  "error": "Missing X-Transaction-Id header",
  "status": 400
}
```
---

### 2.3 Interceptores

Los interceptores actúan a nivel de Spring MVC, después del filtro pero antes y después del controller.
Hemos implementado el `PerformanceInterceptor` que mide el tiempo de respuesta usando `ThreadLocal` para garantizar la seguridad entre hilos.

#### Flujo del PerformanceInterceptor

```
preHandle()        → guarda System.currentTimeMillis() en ThreadLocal
      ↓
      Controller ejecuta el proceso de pago
      ↓
afterCompletion()  → calcula el tiempo total y lo loguea
```

Log esperado tras cada petición:

```
DEBUG PerformanceInterceptor : Tiempo total de respuesta : 75 ms
```

---

### 2.4 Manejo de excepciones global

En lugar de gestionar los errores en cada controller por separado, centralizamos el manejo de excepciones con `@RestControllerAdvice`. Cuando cualquier parte de la aplicación lanza una `PaymentException`, Spring la intercepta automáticamente y devuelve una respuesta estructurada al cliente.

**`PaymentException`** — excepción de negocio con un código identificador:

```java
public class PaymentException extends RuntimeException {

    private String code;

    public PaymentException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
```

**`ErrorPaymentResponse`** — el modelo que se serializa como JSON en la respuesta de error:

```java
public class ErrorPaymentResponse {
    private String code;
    private String message;
    // constructor, getters y setters
}
```

**`ExceptionControllerAdvice`** — captura la excepción y construye la respuesta `HTTP 400`:

```java
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity handlePaymentException(PaymentException exception) {
        ErrorPaymentResponse error = new ErrorPaymentResponse(exception.getCode(), exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
```

Por ejemplo, en `MockProcessor` si el amount es negativo se lanza la excepción con su código:

```java
if (request.getAmount() < 0)
    throw new PaymentException("MockProcessor.Request.amount", "El monto no puede ser negativo");
```

Y la respuesta que llega al cliente es:

```json
{
  "code": "MockProcessor.Request.amount",
  "message": "El monto no puede ser negativo"
}