# Ejercicio1 - Prueba Técnica
Este proyecto contiene algunas pruebas de integración sobre la API Booking, concretamente sobre la función `CreateBooking`.

## Parte 1 - Estrategia de test
En este apartado se citan todos los riesgos y los tests que se pueden realizar sobre la función `CreateBooking`.

*Lista de riesgos:*
- Exposición de datos sensibles: hay que ir con cuidado con los datos que subimos al servidor de Booking.
- Esta función no requiere autentificación

*Lista de tests:*
- Código de respuesta: verificar que la aplicación ha realizado correctamente la solicitud y devuelva el resultado exitoso: 200 HTTP (código de respuesta).
- BookingId no nulo: verificar que se crea una reserva nueva en la API con id no nulo.
- Campos de respuesta esperados: verificar que se recibe en la respuesta los campoos esperados.
- Respuesta esperada: verificar que los datos de respuestas son los esperados por el usuario (nombre, apellido, precio total, ...).

## Parte 2 - Automatizar las pruebas
Partiendo de la lista anterior de tests, se han automatizado las pruebas usando el lenguaje Java, concretamente usando JUnit y Rest Assured.
Las pruebas se pueden encontrar en el archivo `BookingTest.java` ubicado en la siguiente ruta `src/test/java`.
