# AndroidAvanzado

### Descripción:
La app consta de 2 secciones: Listado de combinados y combinados favoritos.

Desde el listado de combinados se pueden guardar como favoritos los que se deseen, presentándose en la lista de favoritos (guardados loclamente). 
Los combinados guardados pueden consultarse en todo momento así como borrarse de la lista cuando se desee.

### Errores detectados:

- Por algún motivo, una vez se retorna de la vista detalle a la vista lista, la vista baja el tamaño del status bar, como si realmente
no se estuviera reemplazando en el contendor del activity sino añadiéndose dentro del propio fragment.

- Desde el emulador a veces se me crashea la app, situación que no se me replica en dispositivo.
