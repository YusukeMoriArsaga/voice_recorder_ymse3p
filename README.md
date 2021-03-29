# オーディオレコーダー

# 目次

- [アプリ機能](#アプリ機能)
- [技術スタック](#技術スタック)
- [アーキテクチャ](#アーキテクチャ)

# アプリ機能
* マイクを使用した録音  

* 表示
  * 録音データの一覧表示
  * 再生状態の表示(音源ごとの再生・未再生の状態表示にも対応)
  * Notificationを用いた、リアルタイムでの再生状態通知
  * ダークモードへの対応

* データの操作
  * 録音デーの保存(「録音タイトル」「録音時間」「保存日時」をSQLデータベースに保管)
  * 録音データ削除(「一括削除」「指定されたのデータ削除」に対応)

* 再生操作
  * 任意の音源データ再生(バックグラウンド再生や連続再生にも対応)
  * 通知バーでの再生音源データ切り替え
  * Bluetooth機器(イヤホンやリモコン等)からの再生操作受付

* 他音声アプリとの協調動作
 * 他アプリから音声が流れる場合は、再生をストップ

| Home | Drawer |
|---|---|
|<img src="https://user-images.githubusercontent.com/53045385/112773427-04bafe80-9071-11eb-98b9-7e51ab9cab43.gif" width="240" />|<img src="https://user-images.githubusercontent.com/53045385/112773430-071d5880-9071-11eb-991a-99c437a03aa5.gif" width="240" /> |


# 技術スタック

* jetpack Library
  * Room Database (SQLite)
  * Data Biding & Binding Adapter
  * DataStore
  * Shimmer Recycler View
  * Navigation

* 音源再生
  * Media Session / Media Browser Serivce / Media Controller / Media Browser
  * Exo Player

* 録音
  * Media Recorder

* マルチスレッド・非同期処理
  * Kotlin Coroutines & Flows(Flow、SharedFlow、StateFlow)

* Dependency Injection
  * Dagger Hilt

# アーキテクチャ

## 全体アーキテクチャ

<img width="1000" src="https://user-images.githubusercontent.com/53045385/112752973-a3634300-9010-11eb-983f-63880ff44546.png" />