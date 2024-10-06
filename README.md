## Uso de Coil
Se utiliza la librería **Coil** para cargar imágenes de los campeones desde URLs y aplicar un borde redondeado a las imágenes directamente en el adaptador. Esto mejora la presentación visual de la aplicación.

```kotlin
 binding.championImage.load(champion.imageUrl) {
                transformations(RoundedCornersTransformation(16f))
            }
