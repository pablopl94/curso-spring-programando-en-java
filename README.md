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