package com.example.df.first.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Menu
import android.view.MenuItem
import com.example.df.first.R
import com.example.df.first.adapter.TypeArticlePageAdapter
import com.example.df.first.base.BaseActivity
import com.example.df.first.bean.TreeListResponse
import com.example.df.first.bean.TreeListResponse.Data.Children
import com.example.df.first.constant.Constant
import kotlinx.android.synthetic.main.activity_type_content.*

class TypeContentActivity : BaseActivity() {

    private lateinit var firstTitle: String

    private val list = mutableListOf<Children>()

    private var target: Boolean = false

    private val typeArticlePagerAdapter: TypeArticlePageAdapter by lazy {
        TypeArticlePageAdapter(list, supportFragmentManager)
    }

    override fun setLayoutId(): Int = R.layout.activity_type_content

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        typeSecondToolbar.run {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        intent.extras?.let { extras ->
            target = extras.getBoolean(Constant.CONTENT_TARGET_KEY, false)
            extras.getString(Constant.CONTENT_TITLE_KEY)?.let {
                firstTitle = it
                typeSecondToolbar.title = it
            }
            if (target) {
                list.add(
                        Children(
                                extras.getInt(Constant.CONTENT_CID_KEY, 0),
                                firstTitle, 0, 0, 0, 0, null
                        )
                )
            } else {
                extras.getSerializable(Constant.CONTENT_CHILDREN_DATA_KEY)?.let {
                    val data = it as TreeListResponse.Data
                    data.children?.let { children ->
                        list.addAll(children)
                    }
                }
            }
        }
        typeSecondViewPager.run {
            adapter = typeArticlePagerAdapter
        }
        typeSecondTabs.run {
            setupWithViewPager(typeSecondViewPager)
        }
        typeSecondViewPager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(
                        typeSecondTabs
                )
        )
        typeSecondTabs.addOnTabSelectedListener(
                TabLayout.ViewPagerOnTabSelectedListener(
                        typeSecondViewPager
                )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_type_content, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menuSearch -> {
                Intent(this, SearchActivity::class.java).run {
                    startActivity(this)
                }
                return true
            }
            R.id.menuShare -> {
                Intent().run {
                    action = Intent.ACTION_SEND
                    putExtra(
                            Intent.EXTRA_TEXT,
                            getString(
                                    R.string.share_type_url,
                                    getString(R.string.app_name),
                                    list[typeSecondTabs.selectedTabPosition].name,
                                    list[typeSecondTabs.selectedTabPosition].id
                            )
                    )
                    type = Constant.CONTENT_SHARE_TYPE
                    startActivity(Intent.createChooser(this, getString(R.string.share_title)))
                }
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun cancelRequest() {
    }
}
