package com.codeslayers.smartiv.montoring

import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.model.DripDetails
import kotlinx.android.synthetic.main.cv_assistance_list.view.*

class AssistanceAdapter(val dripList: ArrayList<DripDetails>) :
    RecyclerView.Adapter<AssistanceAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dripDetail: DripDetails) {
            with(itemView) {
                tvPatID.text = dripDetail.patientID
                tvRN.text = dripDetail.roomNumber
                tvBN.text = dripDetail.bedNumber
                tvPatName.text = dripDetail.patientName
                if (dripDetail.dripStatus) {
                    ivPatientDripStatusGreen.visibility = View.VISIBLE
                    ivPatientDripStatusRed.visibility = View.GONE
                } else {
                    ivPatientDripStatusGreen.visibility = View.GONE
                    ivPatientDripStatusRed.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(from(parent.context).inflate(R.layout.cv_assistance_list, parent, false))
    }

    override fun getItemCount() = dripList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dripList[position])
    }
}