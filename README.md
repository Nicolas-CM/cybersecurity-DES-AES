# Práctica de Cifrado DES y AES

## Integrantes

- Davide Flamini Cazaran
- Andres Cabezas Guerrero
- Nicolas Cuellar Molina

## Cambios realizados sobre DESTest.java

1. **Pruebas con 4 vectores de DES**
   - Se agregaron los 4 vectores de prueba para cifrado DES en modo ECB sin padding.
   - El programa recorre los vectores y muestra el resultado cifrado junto al valor esperado.

2. **Desencriptar con DES**
   - Se añadió una opción en el menú para desencriptar (descifrar) mensajes usando DES.
   - Se agregaron 4 vectores de prueba para descifrado, mostrando el texto plano y el valor esperado.

3. **Cifrado con AES-128**
   - Se agregó una opción en el menú para cifrar usando el algoritmo AES-128.
   - Se incluyeron 4 vectores de prueba para AES-128, mostrando el resultado cifrado y el valor esperado.

4. **BONUS: Cifrado con AES-256**
   - Se añadió una opción en el menú para cifrar usando el algoritmo AES-256.
   - Se agregaron 4 vectores de prueba para AES-256, mostrando el resultado cifrado y el valor esperado.

## Uso del programa

1. Compilar:

   ```sh
   javac DESTest.java
   ```

2. Ejecutar:

   ```sh
   java DESTest
   ```

3. Seleccionar la opción deseada en el menú interactivo.

## Notas

- El programa utiliza las clases estándar de Java para realizar el cifrado y descifrado.
- Los vectores de prueba se pueden modificar en el código según los requerimientos del curso.
- Para AES-256, se requiere que Java tenga soporte para claves de 256 bits (Java 8+ lo soporta por defecto).
