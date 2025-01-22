class GlyphAdapter : ListAdapter<Int, GlyphAdapter.GlyphViewHolder>(DiffCallback()) {

    class GlyphViewHolder(val binding: ItemGlyphBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlyphViewHolder {
        val binding = ItemGlyphBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GlyphViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GlyphViewHolder, position: Int) {
        val codePoint = getItem(position)
        holder.binding.glyphTextView.text = String(Character.toChars(codePoint))
    }

    class DiffCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
    }
}
