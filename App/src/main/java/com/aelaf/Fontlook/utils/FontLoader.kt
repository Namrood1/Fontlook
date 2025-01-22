object FontLoader {
    fun loadFont(context: Context, uri: Uri): Typeface? {
        return try {
            context.contentResolver.openInputStream(uri)?.use { stream ->
                Typeface.createFromInputStream(stream)
            }
        } catch (e: Exception) {
            null
        }
    }
    
    private fun Typeface.getGlyphBounds(codePoint: Int): Rect? {
        return try {
            val paint = Paint().apply { typeface = this@getGlyphBounds }
            val rect = Rect()
            paint.getTextBounds(String(Character.toChars(codePoint)), 0, 1, rect)
            rect
        } catch (e: Exception) {
            null
        }
    }
}
