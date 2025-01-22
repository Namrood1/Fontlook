class FontGlyphsFragment : Fragment() {
    private var _binding: FragmentFontGlyphsBinding? = null
    private val binding get() = _binding!!
    private lateinit var glyphAdapter: GlyphAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFontGlyphsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    fun updateFont(typeface: Typeface) {
        glyphAdapter.submitList(
            (0x0000..0xFFFF).mapNotNull { code ->
                try {
                    if (typeface.getGlyphBounds(code) != null) code else null
                } catch (e: Exception) {
                    null
                }
            }
        )
        binding.glyphRecyclerView.typeface = typeface
    }

    private fun setupRecyclerView() {
        glyphAdapter = GlyphAdapter()
        binding.glyphRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 6)
            adapter = glyphAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.HORIZONTAL
                ).apply {
                    setDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.divider
                        )!!
                    )
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
