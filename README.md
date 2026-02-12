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

**¡A darle caña al código! 💻🔥**
