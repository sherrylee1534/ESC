package tech.sutd.indoortrackingpro.ui.mapping

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.transition.Slide
import dagger.hilt.android.AndroidEntryPoint
import tech.sutd.indoortrackingpro.R
import tech.sutd.indoortrackingpro.databinding.FragmentCoordinatesListBinding
import tech.sutd.indoortrackingpro.databinding.FragmentMappingBinding
import tech.sutd.indoortrackingpro.ui.MainActivity

@AndroidEntryPoint
class MappingFragment : Fragment() {
    private lateinit var location: FloatArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentMappingBinding>(
            inflater,
            R.layout.fragment_mapping,
            container,
            false
        )

        with(binding) {
            map.setImageResource(R.drawable.map)

            map.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    if (event?.action == MotionEvent.ACTION_UP) {
                        location = floatArrayOf(event.x, event.y)
                        Log.d(ContentValues.TAG, "onCreate: ${location[0]}, ${location[1]}")
                        Toast.makeText(
                            this@MappingFragment.context,
                            "Current location is: ${location[0]}, ${location[1]}",
                            Toast.LENGTH_SHORT
                        ).show()

                        map.isEnabled = true
                        map.pos = location
                        map.invalidate()
                        return true
                    }
                    return false
                }
            })

//            val addMappingButton = findViewById<FloatingActionButton>(R.id.fab)
            fab.setOnClickListener {
                (activity as MainActivity).setFloatingActionBtn()
            }

//            // Tracking Button to go from mappingFragment -> trackingFragment
//            trackingButton.setOnClickListener{ view ->
//                view?.findNavController()?.navigate(R.id.action_mappingFragment_to_trackingFragment)
//            }

            return binding.root
        }
    }

}