package ru.burtsev.push_keeper.data.db.app

import ru.burtsev.push_keeper.domain.model.app.AppInfo

object AppInfoMapper {

    fun mapToDomain(list: List<AppEntity>): List<AppInfo> {
        return list.map { mapToDomain(it) }
    }

    private fun mapToDomain(entity: AppEntity): AppInfo {
        return AppInfo(
            id = entity.id,
            packages = entity.packages,
            appName = entity.appName,
            isEnabled = entity.isEnabled,
        )

    }
}
