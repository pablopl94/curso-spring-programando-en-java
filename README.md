# Repositorio de tareas: De Programador a Arquitecto Java

¡Bienvenido a tu repositorio personal del Bootcamp! Este no es un proyecto de juguete.
Es el laboratorio donde vas a aplicar las tareas de nuestros módulos y poder darte feedback personalmente sobre una base
de **Spring Boot 4.0**.

Aquí es donde la teoría de las sesiones que hemos visto pasará a la forma práctica. Si estás aquí, es porque has
decidido que quieres dar un impulso a tu carrera profesional y empezar a pensar como un arquitecto de software. ¡Y te
prometo que juntos los conseguiremos, además de nos lo pasaremos bien en el camino!

---

## 🛠️ Stack Tecnológico

Para este bootcamp, nos movemos en el estándar de la industria de alto rendimiento:

* **Java 21** (LTS)
* **Spring Boot 4.0+**
* **Maven** (Gestor de dependencias)

---

## 📈 Flujo de Trabajo Semanal

Cada semana recibirás un nuevo desafío. El flujo de entrega es estrictamente profesional, simulando un entorno de
trabajo real:

* **Sync:** Asegúrate de tener tu rama `main` actualizada.
* **Branch:** Crea una rama para la tarea semanal: `git checkout -b feature/semana-X-nombre-tarea`.
* **PR:** Sube tus cambios y abre una *Pull Request* hacia la rama `main`.
* **Review:** Revisaré tu código y te daré feedback antes de dar el OK (Merge).

---
## 💬 Soporte y Mentoría

Si te quedas bloqueado:

1. Revisa las clases en la plataforma https://codeja.dev en el apartado del Bootcamp.
2. Pregunta en el canal de **Discord** de la comunidad (¡donde todos aprendemos!).
3. Si es un error técnico persistente, abre un **Issue** en este repositorio detallando el error y qué has intentado
   para solucionarlo.
4. Contactame por email, WhatsApp o Discord.

---

**¡A darle caña al código! 💻🔥**

---

## SEMANA 1 - COMANDOS PARA EJECUTAR LA APLICACIÓN

---

### Entorno de desarrollo (MockProcessor, puerto 8081)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
Resultado esperado en consola:
```
CodeJa levantado con éxito en el perfil: [dev]
La pasarela de pago activa es: mock
```

---

### Entorno de producción con Stripe (puerto 443)
Antes de arrancar en producción, debemos asegurarnos de que el `application.yml` tiene configurado el proveedor de pagos correcto:
```yaml
app:
  payment-provider: stripe  # Cambia a "paypal" si quieres usar PayPal
```
Después definimos las API keys como variables de entorno:
```bash
# Mac/Linux
export STRIPE_API_KEY=ejemplo-xxxx
export PAYPAL_API_KEY=ejemplo-xxxx

# Windows
set STRIPE_API_KEY=ejemplo-xxxx
set PAYPAL_API_KEY=ejemplo-xxxx
```
Después ejecutamos la aplicación con el perfil de producción:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```
Resultado esperado en consola:
```
CodeJa levantado con éxito en el perfil: [prod]
La pasarela de pago activa es: stripe
```

---

### Entorno de producción con PayPal (puerto 443)
Cambiamos el proveedor en el `application.yml`:
```yaml
app:
  payment-provider: paypal  # Cambia a "stripe" si quieres usar Stripe
```
Después definimos las API keys como variables de entorno:
```bash
# Mac/Linux
export STRIPE_API_KEY=ejemplo-xxxx
export PAYPAL_API_KEY=ejemplo-xxxx

# Windows
set STRIPE_API_KEY=ejemplo-xxxx
set PAYPAL_API_KEY=ejemplo-xxxx
```
Después ejecutamos la aplicación con el perfil de producción:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```
Resultado esperado en consola:
```
CodeJa levantado con éxito en el perfil: [prod]
La pasarela de pago activa es: paypal
```
---
# SEMANA 2 — CICLO DE VIDA, INTERCEPTORES Y FILTROS
---

## Introducción

Se ha creado un endpoint `/checkout` en la ruta `api/order` para validar todo lo aprendido durante la semana:

- **Ciclo de vida del Bean** con `@PostConstruct` y `@PreDestroy`
- **Filtros** con validación del header `X-Transaction-Id`
- **Interceptores** con medición del tiempo de respuesta
- **Eventos asíncronos** con `@Async` y `@EventListener`

> Para ver todos los logs activa el perfil `dev` que ya lleva el nivel DEBUG configurado. 
>  Para ver cómo activar los perfiles → [Semana 1 — Comandos para ejecutar la aplicación](#semana-1---comandos-para-ejecutar-la-aplicación)

---

## 2.1 CICLO DE VIDA DE UN BEAN

Spring gestiona el ciclo de vida de cada bean desde que arranca la aplicación hasta que se apaga. Nos enganchamos a ese ciclo con `@PostConstruct` y `@PreDestroy` para ejecutar lógica en el momento exacto que necesitamos.

Estos métodos están en `AuditablePaymentProcessor` (clase abstracta), así que todos los processors los heredan automáticamente sin repetir código.

### @PostConstruct — Al arrancar

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

### @PreDestroy — Al apagarse

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

## 2.2 FILTROS

Los filtros actúan antes de que la petición llegue al controller. Son el primer punto de entrada y el último de salida. 
Para practicar con los filtros, he implementado un `TransactionFilter` que valida el header `X-Transaction-Id` en cada petición.

### Petición correcta — con header

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

### Petición incorrecta — sin header

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

## 2.3 INTERCEPTORES

Los interceptores actúan a nivel de Spring MVC, después del filtro pero antes y después del controller. 
Hemos implementado el `PerformanceInterceptor` que mide el tiempo de respuesta usando `ThreadLocal` para garantizar la seguridad entre hilos.

### Flujo del PerformanceInterceptor

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

## 2.4 MANEJO DE EXCEPCIONES GLOBAL


---