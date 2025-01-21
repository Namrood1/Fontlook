class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fontPagerAdapter: FontPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupViewPager()
        setupFilePicker()

// handle menu items
override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return true
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.action_about -> showAboutDialog()
    }
    return super.onOptionsItemSelected(item)
}

private fun showAboutDialog() {
    MaterialAlertDialogBuilder(this)
        .setTitle("About")
        .setMessage("Font Viewer v1.0\nCreated by You")
        .setPositiveButton("OK", null)
        .show()
}
        
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        binding.collapsingToolbar.title = getString(R.string.app_name)
    }

    private fun setupViewPager() {
        fontPagerAdapter = FontPagerAdapter(this)
        binding.viewPager.adapter = fontPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "Glyphs"
                1 -> "Info"
                else -> "Preview"
            }
        }.attach()
    }

    private fun setupFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "font/*"
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("font/ttf", "font/otf"))
        }
        startActivityForResult(intent, REQUEST_CODE_FONT_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_FONT_PICK && resultCode == Activity.RESULT_OK) {
            data?.data?.let { fontUri ->
                FontLoader.loadFont(this, fontUri)?.let { typeface ->
                    fontPagerAdapter.updateFont(typeface)
                }
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_FONT_PICK = 1001
    }
}
