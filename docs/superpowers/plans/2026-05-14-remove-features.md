# 移除六大功能模块实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 从 SmsForwarder 应用中移除自动任务、主动控制(服务端/客户端)、内网穿透(Frpc)、应用列表六大功能模块

**Architecture:** 删除约150+个功能专属文件，修改约15个共享文件以移除对已删除功能的引用，更新数据库迁移逻辑移除 Task 和 Frpc 表，更新菜单和字符串资源

**Tech Stack:** Kotlin, Android Room, Gradle

---

## 文件结构总览

### 待删除目录（整个目录删除）
- `fragment/condition/` (9个文件) — 任务条件Fragment
- `fragment/action/` (10个文件) — 任务动作Fragment (注：保留此目录为空)
- `fragment/client/` (9个文件) — 客户端功能Fragment
- `entity/condition/` (8个文件) — 任务条件实体
- `entity/action/` (10个文件) — 任务动作实体
- `server/controller/` (8个文件) — HTTP服务端控制器
- `server/component/` (4个文件) — HTTP服务端组件
- `server/model/` (7个文件) — HTTP服务端模型
- `utils/task/` (3个文件) — 任务工具类

### 待修改的关键共享文件
- `MainActivity.kt` — 菜单导航
- `SplashActivity.kt` — 启动路由
- `App.kt` — 应用初始化
- `Core.kt` — 核心数据访问
- `AppDatabase.kt` — 数据库定义
- `SettingsFragment.kt` — 通用设置页面
- `SettingUtils.kt` — 设置常量
- `Constants.kt` — 全局常量
- `ForegroundService.kt` — 前台服务
- `SmsCommandUtils.kt` — 短信指令
- `AndroidManifest.xml` — 清单文件
- `build.gradle` — 依赖配置
- `arrays.xml` — 菜单数组
- `strings.xml` (中文/英文) — 字符串资源
- `RulesEditFragment.kt` — 规则编辑

---

### Task 1: 删除自动任务(Task)相关Kotlin源文件

**Files:**
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/fragment/TasksFragment.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/fragment/TasksEditFragment.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/activity/TaskActivity.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/adapter/TaskPagingAdapter.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/adapter/TaskRecyclerAdapter.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/adapter/TaskSettingAdapter.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/adapter/spinner/TaskSpinnerAdapter.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/adapter/spinner/TaskSpinnerItem.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/database/entity/Task.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/database/dao/TaskDao.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/database/repository/TaskRepository.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/database/viewmodel/TaskViewModel.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/entity/TaskSetting.kt`

- [ ] **Step 1: 删除上述所有文件**

```bash
cd /Users/zhangjialin/StudioProjects/SmsForwarder
rm app/src/main/kotlin/cn/ppps/forwarder/fragment/TasksFragment.kt
rm app/src/main/kotlin/cn/ppps/forwarder/fragment/TasksEditFragment.kt
rm app/src/main/kotlin/cn/ppps/forwarder/activity/TaskActivity.kt
rm app/src/main/kotlin/cn/ppps/forwarder/adapter/TaskPagingAdapter.kt
rm app/src/main/kotlin/cn/ppps/forwarder/adapter/TaskRecyclerAdapter.kt
rm app/src/main/kotlin/cn/ppps/forwarder/adapter/TaskSettingAdapter.kt
rm app/src/main/kotlin/cn/ppps/forwarder/adapter/spinner/TaskSpinnerAdapter.kt
rm app/src/main/kotlin/cn/ppps/forwarder/adapter/spinner/TaskSpinnerItem.kt
rm app/src/main/kotlin/cn/ppps/forwarder/database/entity/Task.kt
rm app/src/main/kotlin/cn/ppps/forwarder/database/dao/TaskDao.kt
rm app/src/main/kotlin/cn/ppps/forwarder/database/repository/TaskRepository.kt
rm app/src/main/kotlin/cn/ppps/forwarder/database/viewmodel/TaskViewModel.kt
rm app/src/main/kotlin/cn/ppps/forwarder/entity/TaskSetting.kt
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "删除：自动任务相关Kotlin源文件（Fragment/Adapter/Database/Entity）"
```

---

### Task 2: 删除任务条件(Condition)相关文件

**Files:**
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/fragment/condition/` 目录下全部9个文件
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/entity/condition/` 目录下全部8个文件

- [ ] **Step 1: 删除条件Fragment和实体文件**

```bash
rm -r app/src/main/kotlin/cn/ppps/forwarder/fragment/condition/
rm -r app/src/main/kotlin/cn/ppps/forwarder/entity/condition/
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "删除：自动任务条件(Condition)相关Fragment和实体文件"
```

---

### Task 3: 删除任务动作(Action)相关文件

**Files:**
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/fragment/action/` 目录下全部10个文件
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/entity/action/` 目录下全部10个文件

- [ ] **Step 1: 删除动作Fragment和实体文件**

```bash
rm -r app/src/main/kotlin/cn/ppps/forwarder/fragment/action/
rm -r app/src/main/kotlin/cn/ppps/forwarder/entity/action/
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "删除：自动任务动作(Action)相关Fragment和实体文件"
```

---

### Task 4: 删除任务工具类和Workers

**Files:**
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/utils/task/TaskUtils.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/utils/task/ConditionUtils.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/utils/task/CronJobScheduler.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/workers/CronWorker.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/workers/ActionWorker.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/workers/NetworkWorker.kt`

- [ ] **Step 1: 删除工具类目录和Workers**

```bash
rm -r app/src/main/kotlin/cn/ppps/forwarder/utils/task/
rm app/src/main/kotlin/cn/ppps/forwarder/workers/CronWorker.kt
rm app/src/main/kotlin/cn/ppps/forwarder/workers/ActionWorker.kt
rm app/src/main/kotlin/cn/ppps/forwarder/workers/NetworkWorker.kt
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "删除：自动任务工具类和Workers（TaskUtils/ConditionUtils/CronJobScheduler/CronWorker/ActionWorker/NetworkWorker）"
```

---

### Task 5: 删除内网穿透(Frpc)相关Kotlin源文件

**Files:**
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/fragment/FrpcFragment.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/fragment/FrpcEditFragment.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/adapter/FrpcPagingAdapter.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/adapter/FrpcRecyclerAdapter.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/adapter/spinner/FrpcSpinnerAdapter.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/adapter/spinner/FrpcSpinnerItem.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/database/entity/Frpc.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/database/dao/FrpcDao.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/database/repository/FrpcRepository.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/database/viewmodel/FrpcViewModel.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/utils/FrpcUtils.kt`

- [ ] **Step 1: 删除所有Frpc相关Kotlin源文件**

```bash
rm app/src/main/kotlin/cn/ppps/forwarder/fragment/FrpcFragment.kt
rm app/src/main/kotlin/cn/ppps/forwarder/fragment/FrpcEditFragment.kt
rm app/src/main/kotlin/cn/ppps/forwarder/adapter/FrpcPagingAdapter.kt
rm app/src/main/kotlin/cn/ppps/forwarder/adapter/FrpcRecyclerAdapter.kt
rm app/src/main/kotlin/cn/ppps/forwarder/adapter/spinner/FrpcSpinnerAdapter.kt
rm app/src/main/kotlin/cn/ppps/forwarder/adapter/spinner/FrpcSpinnerItem.kt
rm app/src/main/kotlin/cn/ppps/forwarder/database/entity/Frpc.kt
rm app/src/main/kotlin/cn/ppps/forwarder/database/dao/FrpcDao.kt
rm app/src/main/kotlin/cn/ppps/forwarder/database/repository/FrpcRepository.kt
rm app/src/main/kotlin/cn/ppps/forwarder/database/viewmodel/FrpcViewModel.kt
rm app/src/main/kotlin/cn/ppps/forwarder/utils/FrpcUtils.kt
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "删除：内网穿透(Frpc)相关Kotlin源文件"
```

---

### Task 6: 删除应用列表(AppList)相关Kotlin源文件

**Files:**
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/fragment/AppListFragment.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/adapter/AppListAdapter.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/workers/LoadAppListWorker.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/utils/AppInfo.kt`

- [ ] **Step 1: 删除AppList相关文件**

```bash
rm app/src/main/kotlin/cn/ppps/forwarder/fragment/AppListFragment.kt
rm app/src/main/kotlin/cn/ppps/forwarder/adapter/AppListAdapter.kt
rm app/src/main/kotlin/cn/ppps/forwarder/workers/LoadAppListWorker.kt
rm app/src/main/kotlin/cn/ppps/forwarder/utils/AppInfo.kt
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "删除：应用列表(AppList)相关Kotlin源文件"
```

---

### Task 7: 删除服务端(Server)和客户端(Client)Kotlin源文件

**Files:**
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/fragment/ServerFragment.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/fragment/ClientFragment.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/activity/ClientActivity.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/fragment/client/` 目录下全部9个文件
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/service/HttpServerService.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/utils/HttpServerUtils.kt`
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/server/controller/` 目录下全部8个文件
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/server/component/` 目录下全部4个文件
- Delete: `app/src/main/kotlin/cn/ppps/forwarder/server/model/` 目录下全部7个文件

- [ ] **Step 1: 删除Server/Client所有Kotlin源文件**

```bash
rm app/src/main/kotlin/cn/ppps/forwarder/fragment/ServerFragment.kt
rm app/src/main/kotlin/cn/ppps/forwarder/fragment/ClientFragment.kt
rm app/src/main/kotlin/cn/ppps/forwarder/activity/ClientActivity.kt
rm -r app/src/main/kotlin/cn/ppps/forwarder/fragment/client/
rm app/src/main/kotlin/cn/ppps/forwarder/service/HttpServerService.kt
rm app/src/main/kotlin/cn/ppps/forwarder/utils/HttpServerUtils.kt
rm -r app/src/main/kotlin/cn/ppps/forwarder/server/
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "删除：服务端(Server)和客户端(Client)所有Kotlin源文件"
```

---

### Task 8: 删除六大功能对应的布局(Layout)XML文件

**Files:**
- Delete: `res/layout/fragment_tasks.xml`
- Delete: `res/layout/fragment_tasks_edit.xml`
- Delete: `res/layout/fragment_tasks_condition_*.xml` (11个)
- Delete: `res/layout/fragment_tasks_action_*.xml` (12个)
- Delete: `res/layout/dialog_task_condition_bottom_sheet.xml`
- Delete: `res/layout/dialog_task_action_bottom_sheet.xml`
- Delete: `res/layout/adapter_task_*.xml` (3个)
- Delete: `res/layout/fragment_frpcs.xml`
- Delete: `res/layout/fragment_frpc_edit.xml`
- Delete: `res/layout/dialog_frpc_save.xml`
- Delete: `res/layout/adapter_frpc_*.xml` (2个)
- Delete: `res/layout/fragment_server.xml`
- Delete: `res/layout/fragment_client*.xml` (10个)
- Delete: `res/layout/fragment_app_list.xml`
- Delete: `res/layout/adapter_app_list_item.xml`

- [ ] **Step 1: 删除所有相关布局文件**

```bash
cd /Users/zhangjialin/StudioProjects/SmsForwarder/app/src/main/res/layout
rm -f fragment_tasks.xml fragment_tasks_edit.xml
rm -f fragment_tasks_condition_*.xml fragment_tasks_action_*.xml
rm -f dialog_task_condition_bottom_sheet.xml dialog_task_action_bottom_sheet.xml
rm -f adapter_task_list_item.xml adapter_task_setting_item.xml adapter_tasks_card_view_list_item.xml
rm -f fragment_frpcs.xml fragment_frpc_edit.xml dialog_frpc_save.xml
rm -f adapter_frpc_list_item.xml adapter_frpcs_card_view_list_item.xml
rm -f fragment_server.xml
rm -f fragment_client*.xml
rm -f fragment_app_list.xml adapter_app_list_item.xml
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "删除：六大功能对应的布局XML文件"
```

---

### Task 9: 删除六大功能对应的Drawable资源

**Files:**
- Delete: `res/drawable/ic_menu_task.xml`
- Delete: `res/drawable/ic_menu_server.xml`
- Delete: `res/drawable/ic_menu_client.xml`
- Delete: `res/drawable/ic_menu_frpc.xml`
- Delete: `res/drawable/ic_menu_app.xml`
- Delete: `res/drawable/auto_task_icon_*.xml` (所有 ~48个)

- [ ] **Step 1: 删除菜单图标和任务图标**

```bash
cd /Users/zhangjialin/StudioProjects/SmsForwarder/app/src/main/res/drawable
rm -f ic_menu_task.xml ic_menu_server.xml ic_menu_client.xml ic_menu_frpc.xml ic_menu_app.xml
rm -f auto_task_icon_*.xml
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "删除：六大功能对应的Drawable资源文件"
```

---

### Task 10: 删除raw资源文件

**Files:**
- Delete: `res/raw/frpc.toml`

- [ ] **Step 1: 删除frpc.toml**

```bash
rm /Users/zhangjialin/StudioProjects/SmsForwarder/app/src/main/res/raw/frpc.toml
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "删除：内网穿透(Frpc)模板TOML文件"
```

---

### Task 11: 修改MainActivity.kt — 移除菜单导航

**Files:**
- Modify: `app/src/main/kotlin/cn/ppps/forwarder/activity/MainActivity.kt`

- [ ] **Step 1: 移除已删除功能的import语句**

```kotlin
// 删除以下import行 (行30-38):
import cn.ppps.forwarder.fragment.AppListFragment
import cn.ppps.forwarder.fragment.ClientFragment
import cn.ppps.forwarder.fragment.FrpcFragment
import cn.ppps.forwarder.fragment.ServerFragment
import cn.ppps.forwarder.fragment.TasksFragment
// 删除以下import行:
import cn.ppps.forwarder.utils.EVENT_LOAD_APP_LIST
import cn.ppps.forwarder.utils.FRPC_LIB_DOWNLOAD_URL
import cn.ppps.forwarder.utils.FRPC_LIB_VERSION
// 删除以下import行 (行50):
import cn.ppps.forwarder.workers.LoadAppListWorker
// 删除以下import行:
import com.xuexiang.xhttp2.XHttp
import com.xuexiang.xhttp2.callback.DownloadProgressCallBack
import com.xuexiang.xhttp2.exception.ApiException
```

- [ ] **Step 2: 修改类成员变量**

```kotlin
// 删除行79-83和86:
private val POS_TASK = 5
private val POS_SERVER = 6
private val POS_CLIENT = 7
private val POS_FRPC = 8
private val POS_APPS = 9
private var needToAppListFragment = false
```

```kotlin
// 修改POS_HELP和POS_ABOUT为连续值:
private val POS_HELP = 5
private val POS_ABOUT = 6
```

- [ ] **Step 3: 修改onCreate方法 — 移除LiveEventBus监听和needToAppListFragment相关代码**

```kotlin
// 删除行141-146:
//监听已安装App信息列表加载完成事件
LiveEventBus.get(EVENT_LOAD_APP_LIST, String::class.java).observe(this) {
    if (needToAppListFragment) {
        openNewPage(AppListFragment::class.java)
    }
}
```

- [ ] **Step 4: 修改initTab方法 — 移除needToAppListFragment**

```kotlin
// 在onTabSelected中删除行167:
needToAppListFragment = false
```

- [ ] **Step 5: 修改initSlidingMenu方法 — 简化菜单项列表**

将行218-233的菜单列表修改为:
```kotlin
mAdapter = DrawerAdapter(
    mutableListOf(
        createItemFor(POS_LOG).setChecked(true),
        createItemFor(POS_RULE),
        createItemFor(POS_SENDER),
        createItemFor(POS_SETTING),
        SpaceItem(15),
        createItemFor(POS_HELP),
        createItemFor(POS_ABOUT),
    )
)
```

- [ ] **Step 6: 修改onItemSelected方法**

将行253-321的整个方法替换为:
```kotlin
override fun onItemSelected(position: Int) {
    when (position) {
        POS_LOG, POS_RULE, POS_SENDER, POS_SETTING -> {
            val tab = mTabLayout.getTabAt(position)
            tab?.select()
            mSlidingRootNav.closeMenu()
        }
        POS_HELP -> AgentWebActivity.goWeb(this, getString(R.string.url_help))
        POS_ABOUT -> openNewPage(AboutFragment::class.java)
    }
}
```

- [ ] **Step 7: 删除downloadFrpcLib方法和相关import**

删除行332-393的整个 `downloadFrpcLib()` 方法。

- [ ] **Step 8: 提交**

```bash
git add -A
git commit -m "修改：MainActivity移除所有六大功能的菜单导航、Frpc下载和AppList监听"
```

---

### Task 12: 修改SplashActivity.kt — 简化启动路由

**Files:**
- Modify: `app/src/main/kotlin/cn/ppps/forwarder/activity/SplashActivity.kt`

- [ ] **Step 1: 修改whereToJump方法**

将行49-57替换为:
```kotlin
private fun whereToJump() {
    ActivityUtils.startActivity(MainActivity::class.java)
    finish()
}
```

同时删除不再需要的import:
```kotlin
// 删除:
import cn.ppps.forwarder.utils.SettingUtils
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "修改：SplashActivity移除纯任务/纯客户端模式路由"
```

---

### Task 13: 修改App.kt — 清理应用初始化代码

**Files:**
- Modify: `app/src/main/kotlin/cn/ppps/forwarder/App.kt`

- [ ] **Step 1: 移除已删除功能的import**

```kotlin
// 删除以下import行:
import cn.ppps.forwarder.database.repository.FrpcRepository  // 行27
import cn.ppps.forwarder.database.repository.TaskRepository  // 行32
import cn.ppps.forwarder.service.HttpServerService            // 行41
import cn.ppps.forwarder.utils.AppInfo                        // 行44
import cn.ppps.forwarder.utils.FRPC_LIB_VERSION               // 行49
import cn.ppps.forwarder.utils.HttpServerUtils                // 行51
import cn.ppps.forwarder.utils.sdkinit.UMengInit             // 行56 (如果仅用于统计保留)
import cn.ppps.forwarder.utils.tinker.TinkerLoadLibrary      // 行59
import com.king.location.LocationClient                      // 行60
import frpclib.Frpclib                                        // 行62
```

- [ ] **Step 2: 删除lazy属性**

```kotlin
// 删除行84和89:
val frpcRepository by lazy { FrpcRepository(database.frpcDao()) }
val taskRepository by lazy { TaskRepository(database.taskDao()) }
```

- [ ] **Step 3: 删除companion object中的AppList和Frpclib相关字段**

```kotlin
// 删除行118-121:
var LoadingAppList = false
var UserAppList: MutableList<AppInfo> = mutableListOf()
var SystemAppList: MutableList<AppInfo> = mutableListOf()

// 删除行141:
var FrpclibInited = false
```

- [ ] **Step 4: 修改onCreate方法 — 删除FrpcLib动态加载、HttpServer启动、AppList加载相关代码**

删除行184-199:
```kotlin
// 删除整个 FrpcLib 加载代码块 (行188-199):
//纯客户端模式
if (SettingUtils.enablePureClientMode) return
//动态加载FrpcLib
val libPath = filesDir.absolutePath + "/libs"
...
```

删除行210-215 (HttpServer自启动):
```kotlin
//启动HttpServer
if (HttpServerUtils.enableServerAutorun) {
    Intent(this, HttpServerService::class.java).also {
        startService(it)
    }
}
```

- [ ] **Step 5: 提交**

```bash
git add -A
git commit -m "修改：App.kt清理FrpcLib加载、HttpServer启动、AppList和Task/Frpc Repository初始化代码"
```

---

### Task 14: 修改Core.kt — 移除Task和Frpc Repository引用

**Files:**
- Modify: `app/src/main/kotlin/cn/ppps/forwarder/core/Core.kt`

- [ ] **Step 1: 修改Core.kt**

删除以下行:
```kotlin
// 删除行7 (import):
import cn.ppps.forwarder.database.repository.FrpcRepository

// 删除行12 (import):
import cn.ppps.forwarder.database.repository.TaskRepository

// 删除行18 (属性):
val frpc: FrpcRepository by lazy { (app as App).frpcRepository }

// 删除行23 (属性):
val task: TaskRepository by lazy { (app as App).taskRepository }
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "修改：Core.kt移除Task和Frpc Repository引用"
```

---

### Task 15: 修改AppDatabase.kt — 移除Task和Frpc表和DAO

**Files:**
- Modify: `app/src/main/kotlin/cn/ppps/forwarder/database/AppDatabase.kt`

- [ ] **Step 1: 修改AppDatabase.kt**

修改@Database注解移除Frpc和Task实体，修改版本号:
```kotlin
@Database(
    entities = [Msg::class, Logs::class, Rule::class, Sender::class],
    views = [LogsDetail::class],
    version = 22,
    exportSchema = false
)
```

移除已删除的import (行11, 16, 22):
```kotlin
// 删除:
import cn.ppps.forwarder.database.dao.FrpcDao
import cn.ppps.forwarder.database.dao.TaskDao
import cn.ppps.forwarder.database.entity.Frpc
import cn.ppps.forwarder.database.entity.Task
```

移除DAO方法 (行37, 42):
```kotlin
// 删除:
abstract fun frpcDao(): FrpcDao
abstract fun taskDao(): TaskDao
```

修改onCreate回调删除Frpc默认数据插入 (行60-92，删除整个execSQL调用块):
```kotlin
override fun onCreate(db: SupportSQLiteDatabase) {
    // 移除默认Frpc数据插入
}
```

在迁移列表末尾添加MIGRATION_21_22删除Task和Frpc表:
```kotlin
private val MIGRATION_21_22 = object : Migration(21, 22) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE IF EXISTS Task")
        database.execSQL("DROP TABLE IF EXISTS Frpc")
    }
}
```

并在buildDatabase的addMigrations列表中加入:
```kotlin
MIGRATION_21_22,
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "修改：AppDatabase移除Task和Frpc实体/DAO，新增数据库迁移删除相关表"
```

---

### Task 16: 修改SettingUtils.kt — 移除相关设置项

**Files:**
- Modify: `app/src/main/kotlin/cn/ppps/forwarder/utils/SettingUtils.kt`

- [ ] **Step 1: 删除以下设置项**

```kotlin
// 删除 (行62-69):
var enableLoadAppList: Boolean by SharedPreference(ENABLE_LOAD_APP_LIST, false)
var enableLoadUserAppList: Boolean by SharedPreference(ENABLE_LOAD_USER_APP_LIST, false)
var enableLoadSystemAppList: Boolean by SharedPreference(ENABLE_LOAD_SYSTEM_APP_LIST, false)

// 删除 (行128-132):
var enablePureClientMode: Boolean by SharedPreference(SP_PURE_CLIENT_MODE, false)
var enablePureTaskMode: Boolean by SharedPreference(SP_PURE_TASK_MODE, false)
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "修改：SettingUtils移除纯客户端/纯任务/加载应用列表设置项"
```

---

### Task 17: 修改Constants.kt — 移除相关常量

**Files:**
- Modify: `app/src/main/kotlin/cn/ppps/forwarder/utils/Constants.kt`

- [ ] **Step 1: 删除TaskWorker object和所有任务相关常量**

删除以下常量定义:
- `object TaskWorker` 及其内部 `TASK_ID`, `TASK`, `TASK_CONDITIONS`, `TASK_ACTIONS` (行11-15)
- `ENABLE_LOAD_APP_LIST`, `ENABLE_LOAD_USER_APP_LIST`, `ENABLE_LOAD_SYSTEM_APP_LIST`
- `SP_PURE_CLIENT_MODE`, `SP_PURE_TASK_MODE`
- `EVENT_LOAD_APP_LIST`
- `KEY_TASK_ID`, `KEY_TASK_TYPE`, `KEY_TASK_CLONE`
- `MAX_SETTING_NUM` 及所有 `KEY_TEST_CONDITION` 到 `KEY_BACK_DESCRIPTION_ACTION` 相关常量
- `TASK_CONDITION_*` (12个常量，1000-1011)
- `TASK_ACTION_*` (12个常量，2000-2011)
- `FRPC_LIB_DOWNLOAD_URL`, `FRPC_LIB_VERSION`
- `EVENT_FRPC_UPDATE_CONFIG`, `EVENT_FRPC_DELETE_CONFIG`, `EVENT_FRPC_RUNNING_ERROR`, `EVENT_FRPC_RUNNING_SUCCESS`
- `INTENT_FRPC_EDIT_FILE`, `INTENT_FRPC_APPLY_FILE`
- `EVENT_ALARM_ACTION`
- 所有 `SP_SERVER_*` 常量 (行204-228)
- `SP_ENABLE_API_*` 常量 (行214-222)
- `SP_API_LOCATION_CACHE`, `SP_WOL_HISTORY`, `SP_SERVER_ADDRESS`, `SP_SERVER_HISTORY`, `SP_SERVER_CONFIG`
- `SP_CLIENT_SAFETY_MEASURES`, `SP_CLIENT_SIGN_KEY`
- `HTTP_SERVER_PORT`, `HTTP_SERVER_TIME_OUT`, `HTTP_SUCCESS_CODE`, `HTTP_FAILURE_CODE`

注意: 保留 `FRONT_NOTIFY_ID`, `FRONT_CHANNEL_ID`, `FRONT_CHANNEL_NAME` (前台服务仍需要)

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "修改：Constants.kt移除所有六大功能相关常量定义"
```

---

### Task 18: 修改ForegroundService.kt — 移除Task/Frpc/Alarm相关代码

**Files:**
- Modify: `app/src/main/kotlin/cn/ppps/forwarder/service/ForegroundService.kt`

- [ ] **Step 1: 清理ForegroundService**

删除以下import:
```kotlin
import cn.ppps.forwarder.entity.action.AlarmSetting
import cn.ppps.forwarder.utils.EVENT_ALARM_ACTION
import cn.ppps.forwarder.utils.EVENT_FRPC_RUNNING_ERROR
import cn.ppps.forwarder.utils.EVENT_FRPC_RUNNING_SUCCESS
import cn.ppps.forwarder.utils.FlashUtils
import cn.ppps.forwarder.utils.INTENT_FRPC_APPLY_FILE
import cn.ppps.forwarder.utils.TASK_CONDITION_CRON
import cn.ppps.forwarder.utils.VibrationUtils
import cn.ppps.forwarder.utils.task.CronJobScheduler
import cn.ppps.forwarder.workers.LoadAppListWorker
import frpclib.Frpclib
```

删除frpcObserver字段 (行69-95) - 整个CompositeDisposable和frpcObserver。
删除alarm相关字段和方法 (行98-200):
- `vibrationUtils`, `isVibrating`, `flashUtils`, `isFlash`
- `alarmPlayer`, `alarmPlayTimes`
- `alarmObserver`

修改`startForegroundService`方法，删除以下逻辑:
- 启动定时任务(Cron) (行277-284)
- 异步获取App信息 (行287-290)
- 启动Frpc和监听Frpc指令 (行292-316)
- 播放警报监听 (行319)

修改后的startForegroundService:
```kotlin
private fun startForegroundService() {
    if (isRunning) return
    isRunning = true

    val notification = createNotification(SettingUtils.notifyContent)
    startForeground(FRONT_NOTIFY_ID, notification)

    try {
        //开关通知监听服务
        if (SettingUtils.enableAppNotify && CommonUtils.isNotificationListenerServiceEnabled(this)) {
            CommonUtils.toggleNotificationListenerService(this)
        }
    } catch (e: Exception) {
        handleException(e, "startForegroundService")
    }
}
```

修改`stopForegroundService`方法，删除alarm/vibration/flash清理代码:
```kotlin
private fun stopForegroundService() {
    try {
        stopForeground(true)
        stopSelf()
        compositeDisposable.dispose()
        isRunning = false
    } catch (e: Exception) {
        handleException(e, "stopForegroundService")
    }
}
```

删除onCreate中初始化的振动和闪光灯 (行217-218):
```kotlin
// 删除:
vibrationUtils = VibrationUtils(this)
flashUtils = FlashUtils(this)
```

移除stopped alarm的ACTION_STOP_ALARM处理 (行242-246):
```kotlin
// 删除:
ACTION_STOP_ALARM -> {
    alarmPlayer?.release()
    alarmPlayer = null
    updateNotification(SettingUtils.notifyContent)
}
```

删除createNotification中的showStopButton参数支持（简化，因为只有警报需要停止按钮）。

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "修改：ForegroundService移除Task定时任务、Frpc自启动、警报播放等代码"
```

---

### Task 19: 修改SettingsFragment.kt — 移除相关设置开关

**Files:**
- Modify: `app/src/main/kotlin/cn/ppps/forwarder/fragment/SettingsFragment.kt`

- [ ] **Step 1: 移除import**

```kotlin
// 删除:
import cn.ppps.forwarder.adapter.spinner.AppListAdapterItem      // 行40
import cn.ppps.forwarder.adapter.spinner.AppListSpinnerAdapter   // 行41
import cn.ppps.forwarder.fragment.client.CloneFragment           // 行45
import cn.ppps.forwarder.utils.EVENT_LOAD_APP_LIST               // 行59
import cn.ppps.forwarder.workers.LoadAppListWorker               // 行70
```

- [ ] **Step 2: 删除或修改相关方法和调用**

删除appListSpinner相关字段 (行98-103):
```kotlin
// 删除:
private val appListSpinnerList = ArrayList<AppListAdapterItem>()
private lateinit var appListSpinnerAdapter: AppListSpinnerAdapter<*>
private val appListObserver = Observer { ... }
```

删除initViews中的相关方法调用:
- 删除行158: `switchEnableLoadAppList(...)` 调用
- 删除行207: `switchDirectlyToClient(...)` 调用
- 删除行209: `switchDirectlyToTask(...)` 调用

删除initListeners中的LiveEventBus监听 (行232):
```kotlin
// 删除:
LiveEventBus.get(EVENT_LOAD_APP_LIST, String::class.java).observeStickyForever(appListObserver)
```

删除onResume中的initAppSpinner调用 (行220-222).

删除titleBar中CloneFragment恢复按钮 (行123-131):
```kotlin
// 删除:
titleBar!!.addAction(object : TitleBar.ImageAction(R.drawable.ic_restore) { ... })
```

删除以下完整方法:
- `switchEnableLoadAppList()` (行802-848)
- `switchDirectlyToClient()` (行1097-1107)
- `switchDirectlyToTask()` (行1110-1120)
- `initAppSpinner()` (行1373-1414)

- [ ] **Step 3: 提交**

```bash
git add -A
git commit -m "修改：SettingsFragment移除加载应用列表、纯客户端/纯任务模式设置项"
```

---

### Task 20: 修改SmsCommandUtils.kt — 移除Frpc和HttpServer引用

**Files:**
- Modify: `app/src/main/kotlin/cn/ppps/forwarder/utils/SmsCommandUtils.kt`

需要完整读取该文件，移除其中与Frpc和HttpServer相关的短信指令处理逻辑。保留其他指令（如果有的话）。

- [ ] **Step 1: 读取SmsCommandUtils.kt完整内容**

先读取完整文件确认所有需要修改的位置。

- [ ] **Step 2: 移除Frpc和HttpServer相关指令处理**

根据实际代码移除相关分支。

- [ ] **Step 3: 提交**

```bash
git add -A
git commit -m "修改：SmsCommandUtils移除Frpc和HttpServer短信指令处理"
```

---

### Task 21: 修改AndroidManifest.xml

**Files:**
- Modify: `app/src/main/AndroidManifest.xml`

- [ ] **Step 1: 删除Activity声明**

删除 TaskActivity:
```xml
<activity
    android:name=".activity.TaskActivity" ... />
```

删除 ClientActivity:
```xml
<activity
    android:name=".activity.ClientActivity" ... />
```

- [ ] **Step 2: 删除HttpServerService**

```xml
<service
    android:name=".service.HttpServerService"
    android:enabled="true" />
```

- [ ] **Step 3: 提交**

```bash
git add -A
git commit -m "修改：AndroidManifest移除TaskActivity/ClientActivity/HttpServerService声明"
```

---

### Task 22: 修改build.gradle — 移除不需要的依赖

**Files:**
- Modify: `app/build.gradle`

- [ ] **Step 1: 移除依赖**

删除以下依赖:
```groovy
// 删除行282 (frpclib):
implementation files('libs/frpclib.aar')

// 删除行402-403 (AndServer):
implementation 'cn.ppps.andserver:api:2.1.12'
kapt 'cn.ppps.andserver:processor:2.1.12'

// 删除行408-409 (cron-parser):
implementation 'gatewayapps.crondroid:crondroid:1.0.0'
implementation 'net.redhogs.cronparser:cron-parser-core:3.5'
```

删除andserver插件 (行11):
```groovy
// 删除:
id 'com.yanzhenjie.andserver'
```

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "修改：build.gradle移除frpclib/AndServer/cron-parser依赖"
```

---

### Task 23: 修改arrays.xml — 简化菜单数组

**Files:**
- Modify: `app/src/main/res/values/arrays.xml`

- [ ] **Step 1: 修改menu_titles数组**

将行4-18改为:
```xml
<string-array name="menu_titles">
    <item>@string/menu_logs_step</item>
    <item>@string/menu_rules_step</item>
    <item>@string/menu_senders_step</item>
    <item>@string/menu_settings_step</item>
    <item />
    <item>@string/menu_help</item>
    <item>@string/menu_about</item>
</string-array>
```

- [ ] **Step 2: 修改menu_icons数组**

将行20-34改为:
```xml
<array name="menu_icons">
    <item>@drawable/ic_menu_logs</item>
    <item>@drawable/ic_menu_rule</item>
    <item>@drawable/ic_menu_send</item>
    <item>@drawable/ic_menu_settings</item>
    <item />
    <item>@drawable/ic_menu_help</item>
    <item>@drawable/ic_menu_about</item>
</array>
```

- [ ] **Step 3: 删除task_type_option数组**

```xml
<!-- 删除行108-111 -->
```

- [ ] **Step 4: 提交**

```bash
git add -A
git commit -m "修改：arrays.xml简化菜单数组，移除task_type_option"
```

---

### Task 24: 修改strings.xml (中文) — 移除相关字符串资源

**Files:**
- Modify: `app/src/main/res/values/strings.xml`

删除以下字符串资源（需要根据实际strings.xml中的确切行确认）:
- `menu_tasks`, `menu_server`, `menu_client`, `menu_frpc`, `menu_apps`
- `about_frpc_version`, `about_frpc_deleted`
- `action_save`, `action_back`, `title_save_config`, `tipServiceRunning` (Frpc相关)
- `add_frpc_first`, `frpc_name`, `frpc_autorun`, `delete_frpc`
- `choose_frpc`, `frpclib_download_title`, `frpclib_download_content`, `frpclib_version_mismatch`
- `frpc_failed_to_run`, `frpc_contains_tips`
- `download_frpc_tips`, `download_frpc_tips2`
- `task_frpc`, `task_frpc_tips`
- `all_auto_started_frpc`, `specified_frpc`
- `http_server`, `start_server`, `stop_server`
- `http_server_running`, `http_server_stopped`, `server_settings`, `server_settings_tips`
- `server_history`, `server_test`, `disabled_on_the_server`
- `server_settings_tips2`, `service_address`, `features_list`
- `pure_client_mode`, `pure_client_mode_tips`, `exit_pure_client_mode`, `enabling_pure_client_mode`
- `web_client`, `restarting_httpserver`
- `select_web_client_directory`, `operating_instruction`
- `task_http_server`, `task_http_server_tips`
- `task_type_option_mine`, `task_type_option_fixed`
- `new_task_first`, `add_task_first`, `choose_task`, `task_contains_tips`
- `pure_task_mode`, `pure_task_mode_tips`
- `task_name_status`, `task_conditions`, `task_conditions_tips`, `task_actions`, `task_actions_tips`
- `add_task`, `edit_task`, `clone_task`, `delete_task_title`, `delete_task_tips`, `delete_task_toast`
- `select_task_trigger`, `select_task_condition`, `select_task_action`
- 所有 `task_cron` 到 `task_wol` 及对应的 `_tips` 字符串
- `specified_task`, `task_condition_check_again`, `task_condition_check_again_tips`
- `invalid_task_name`, `invalid_conditions`, `invalid_actions`, `invalid_cron`
- `load_app_list`, `load_app_list_tips`, `load_app_list_toast`
- `loading_app_list`

- [ ] **Step 1: 使用脚本批量删除字符串**

先读取完整的strings.xml，精确定位需要删除的行，然后逐一删除。

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "修改：strings.xml(中文)移除六大功能相关字符串资源"
```

---

### Task 25: 修改strings.xml (英文) — 同步删除

**Files:**
- Modify: `app/src/main/res/values-en/strings.xml`

- [ ] **Step 1: 删除对应的英文字符串**

与中文版保持一致的删除范围。

- [ ] **Step 2: 提交**

```bash
git add -A
git commit -m "修改：strings.xml(英文)移除六大功能相关字符串资源"
```

---

### Task 26: 修改RulesEditFragment.kt — 移除AppList相关引用

**Files:**
- Modify: `app/src/main/kotlin/cn/ppps/forwarder/fragment/RulesEditFragment.kt`

- [ ] **Step 1: 移除AppListSpinnerAdapter引用**

需要根据实际代码移除AppListSpinner相关引用，改用简单的文本输入（因为删除了AppListAdapterItem和AppListSpinnerAdapter，但规则编辑中的包名选择器仍需要替换方案）。

由于实际上删除了 `AppListSpinnerAdapter` 和 `AppListAdapterItem`，但规则编辑需要使用它们来选择应用包名。这里有两个选择:
1. 保留这两个文件（不删除）
2. 删除这两个文件并修改RulesEditFragment移除包名选择功能

选择方案1: 保留 `AppListSpinnerAdapter.kt` 和 `AppListAdapterItem.kt`，仅删除独立的AppListFragment和AppListAdapter。

实际上在Task 6已经删除了AppListAdapterItem和AppListSpinnerAdapter。需要恢复这两个文件。

- [ ] **Step 1: 恢复AppListSpinnerAdapter.kt和AppListAdapterItem.kt**

使用git恢复:
```bash
git checkout HEAD~N -- app/src/main/kotlin/cn/ppps/forwarder/adapter/spinner/AppListAdapterItem.kt
git checkout HEAD~N -- app/src/main/kotlin/cn/ppps/forwarder/adapter/spinner/AppListSpinnerAdapter.kt
```

- [ ] **Step 2: 提交修复**

```bash
git add -A
git commit -m "修复：恢复AppListSpinnerAdapter和AppListAdapterItem（被RulesEditFragment/SettingsFragment使用）"
```

---

### Task 27: 验证编译

**Files:** 无

- [ ] **Step 1: 尝试编译项目**

```bash
cd /Users/zhangjialin/StudioProjects/SmsForwarder
./gradlew assembleDebug --stacktrace 2>&1 | tail -100
```

- [ ] **Step 2: 根据编译错误修复遗漏的引用**

根据编译错误逐一修复。可能的遗漏:
- 其他Fragment中对已删除类的import
- strings.xml中仍有被引用的字符串
- 布局文件中对已删除字符串的引用

- [ ] **Step 3: 重复编译直到成功**

- [ ] **Step 4: 提交最终修复**

```bash
git add -A
git commit -m "修复：编译错误修复，确保项目可正常构建"
```

---

## 注意事项

1. `AppListAdapterItem.kt` 和 `AppListSpinnerAdapter.kt` 被 SettingsFragment 和 RulesEditFragment 共用，不可删除
2. `SendLogicWorker.kt` 是核心转发逻辑Worker，不属于任务系统，不可删除
3. `fragment/senders/ServerChanFragment.kt` 是发送通道(Server酱)，与服务端(Server)无关，不可删除
4. 数据库迁移MIGRATION_17_18创建了Task表，MIGRATION_9_10创建了Frpc表，新迁移只在version 21→22时执行DROP TABLE
5. 英语strings.xml中的字符串需要与中文版同步删除
