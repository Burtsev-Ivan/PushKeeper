package ru.burtsev.push_keeper.presentation.adapter

import android.content.pm.PackageManager.NameNotFoundException
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.burtsev.push_keeper.data.NotificationEntity
import ru.burtsev.push_keeper.databinding.ItemNotificationBinding


class NotificationAdapter : RecyclerView.Adapter<NotificationViewHolder>() {


    private var notifications: List<NotificationEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationBinding.inflate(inflater, parent, false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    fun setData(notifications: List<NotificationEntity>) {
        this.notifications = notifications
        notifyDataSetChanged()
    }
}

class NotificationViewHolder(private val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(notification: NotificationEntity) {
        binding.tvApplicationName.text = notification.appName
        binding.tvTitle.text = notification.title
        binding.tvText.text = notification.text
        binding.imgAppIcon.setImageDrawable(getAppIcon(notification.packages))


    }

    private fun getAppIcon(packageName: String): Drawable? {

        return try {
            itemView.context.packageManager.getApplicationIcon(packageName)
        } catch (e: NameNotFoundException) {
            null
        }
    }

}