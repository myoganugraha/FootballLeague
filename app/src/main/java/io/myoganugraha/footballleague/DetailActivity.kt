package io.myoganugraha.footballleague

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop
import com.squareup.picasso.Picasso
import io.myoganugraha.footballleague.Model.LeagueModel
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val POSITION: String = "position"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val list = intent.getParcelableExtra<LeagueModel>(POSITION)
        DetailActivityUI(list).setContentView(this)

    }

    class DetailActivityUI(private var list: LeagueModel) : AnkoComponent<DetailActivity> {
        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
            scrollView {
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    lparams(matchParent, matchParent)

                    imageView {
                        Picasso.get().load(list.leagueImage).into(this)
                        id = R.id.id_league_badge
                        padding = dip(10)
                        this@linearLayout.gravity = Gravity.CENTER_HORIZONTAL
                    }.lparams(dip(120), dip(120))

                    textView {
                        id = R.id.id_league_name
                        text = list.leagueName
                        textSize = sp(10).toFloat()
                        gravity = Gravity.CENTER_HORIZONTAL
                        padding = dip(8)
                        typeface = Typeface.DEFAULT_BOLD

                    }

                    textView {
                        id = R.id.id_league_desc
                        text = list.leageDescription
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        padding = dip(8)
                    }
                }
            }
        }

    }
}