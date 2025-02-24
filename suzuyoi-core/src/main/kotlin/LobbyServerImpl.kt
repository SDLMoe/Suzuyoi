/**
 * Copyright 2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the MIT license which
 * accompanies this distribution and is available at
 *
 * https://github.com/SDLMoe/Suzuyoi/blob/main/LICENSE
 */
package moe.sdl.suzuyoi

import moe.sdl.suzuyoi.protos.*
import java.time.Instant

object LobbyServerImpl : LobbyServer {
  override suspend fun fetchConnectionInfo(request: ReqCommon): ResConnectionInfo = TODO("Not yet implemented")

  override suspend fun fetchQueueInfo(request: ReqCommon): ResFetchQueueInfo = TODO("Not yet implemented")

  override suspend fun cancelQueue(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun openidCheck(request: ReqOpenidCheck): ResOauth2Check = TODO("Not yet implemented")

  override suspend fun signup(request: ReqSignupAccount): ResSignupAccount = TODO("Not yet implemented")

  override suspend fun login(request: ReqLogin): ResLogin {
    val account = request.account
    val password = request.password

    return ResLogin(
      error =
        Error(
          u32_params = emptyList(),
          str_params = emptyList(),
          code = 0,
        ),
      logined_version = emptyList(),
      rewarded_version = emptyList(),
      account_id = 114514,
      account =
        Account(
          platform_diamond =
            buildList {
              add(
                Account.PlatformDiamond(
                  id = 100001,
                  count = 114514,
                ),
              )
              addAll(
                (101001..101011).toList().map {
                  Account.PlatformDiamond(
                    id = it,
                    count = 114514,
                  )
                },
              )
            },
          platform_skin_ticket =
            buildList {
              add(
                Account.PlatformSkinTicket(
                  id = 100004,
                  count = 114514,
                ),
              )
              addAll(
                (102001..102011).toList().map {
                  Account.PlatformSkinTicket(
                    id = it,
                    count = 114514,
                  )
                },
              )
            },
          challenge_levels = emptyList(),
          achievement_count =
            listOf(
              Account.AchievementCount(
                rare = 1,
                count = 0,
              ),
              Account.AchievementCount(
                rare = 2,
                count = 0,
              ),
              Account.AchievementCount(
                rare = 3,
                count = 0,
              ),
            ),
          loading_image = listOf(230501, 240801),
          favorite_hu = emptyList(),
          badges = emptyList(),
          account_id = 114514,
          nickname = "我是雀士",
          login_time = Instant.now().nano,
          logout_time = 0,
          room_id = 0,
          anti_addiction = null,
          title = 60094,
          signature = "原神启动",
          email = "user@example.com",
          email_verify = 1,
          gold = 114514,
          diamond = 0,
          avatar_id = 400502,
          vip = 114514,
          birthday = 0,
          phone = "",
          phone_verify = 0,
          level =
            AccountLevel(
              id = 30301,
              score = 1,
            ),
          level3 =
            AccountLevel(
              id = 30301,
              score = 1,
            ),
          avatar_frame = 0,
          skin_ticket = 0,
          verified = 1,
          frozen_state = 0,
        ),
      game_info = null,
      has_unread_announcement = false,
      access_token = "genshin_impact",
      signup_time = 0,
      is_id_card_authed = true,
      country = "US",
    )
  }

  override suspend fun fetchInfo(request: ReqCommon): ResFetchInfo = TODO("Not yet implemented")

  override suspend fun loginSuccess(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchServerMaintenanceInfo(request: ReqCommon): ResFetchServerMaintenanceInfo = TODO("Not yet implemented")

  override suspend fun emailLogin(request: ReqEmailLogin): ResLogin = TODO("Not yet implemented")

  override suspend fun oauth2Auth(request: ReqOauth2Auth): ResOauth2Auth = TODO("Not yet implemented")

  override suspend fun oauth2Check(request: ReqOauth2Check): ResOauth2Check = TODO("Not yet implemented")

  override suspend fun oauth2Signup(request: ReqOauth2Signup): ResOauth2Signup = TODO("Not yet implemented")

  override suspend fun oauth2Login(request: ReqOauth2Login): ResLogin = TODO("Not yet implemented")

  override suspend fun dmmPreLogin(request: ReqDMMPreLogin): ResDMMPreLogin = TODO("Not yet implemented")

  override suspend fun createPhoneVerifyCode(request: ReqCreatePhoneVerifyCode): ResCommon = TODO("Not yet implemented")

  override suspend fun createEmailVerifyCode(request: ReqCreateEmailVerifyCode): ResCommon = TODO("Not yet implemented")

  override suspend fun verfifyCodeForSecure(request: ReqVerifyCodeForSecure): ResVerfiyCodeForSecure = TODO("Not yet implemented")

  override suspend fun bindPhoneNumber(request: ReqBindPhoneNumber): ResCommon = TODO("Not yet implemented")

  override suspend fun unbindPhoneNumber(request: ReqUnbindPhoneNumber): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchPhoneLoginBind(request: ReqCommon): ResFetchPhoneLoginBind = TODO("Not yet implemented")

  override suspend fun createPhoneLoginBind(request: ReqCreatePhoneLoginBind): ResCommon = TODO("Not yet implemented")

  override suspend fun bindEmail(request: ReqBindEmail): ResCommon = TODO("Not yet implemented")

  override suspend fun modifyPassword(request: ReqModifyPassword): ResCommon = TODO("Not yet implemented")

  override suspend fun bindAccount(request: ReqBindAccount): ResCommon = TODO("Not yet implemented")

  override suspend fun logout(request: ReqLogout): ResLogout = TODO("Not yet implemented")

  override suspend fun heatbeat(request: ReqHeatBeat): ResCommon = TODO("Not yet implemented")

  override suspend fun loginBeat(request: ReqLoginBeat): ResCommon = TODO("Not yet implemented")

  override suspend fun createNickname(request: ReqCreateNickname): ResCommon = TODO("Not yet implemented")

  override suspend fun modifyNickname(request: ReqModifyNickname): ResCommon = TODO("Not yet implemented")

  override suspend fun modifyBirthday(request: ReqModifyBirthday): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchRoom(request: ReqCommon): ResSelfRoom = TODO("Not yet implemented")

  override suspend fun fetchGamingInfo(request: ReqCommon): ResFetchGamingInfo = TODO("Not yet implemented")

  override suspend fun createRoom(request: ReqCreateRoom): ResCreateRoom = TODO("Not yet implemented")

  override suspend fun joinRoom(request: ReqJoinRoom): ResJoinRoom = TODO("Not yet implemented")

  override suspend fun leaveRoom(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun readyPlay(request: ReqRoomReady): ResCommon = TODO("Not yet implemented")

  override suspend fun dressingStatus(request: ReqRoomDressing): ResCommon = TODO("Not yet implemented")

  override suspend fun startRoom(request: ReqRoomStart): ResCommon = TODO("Not yet implemented")

  override suspend fun roomKickPlayer(request: ReqRoomKickPlayer): ResCommon = TODO("Not yet implemented")

  override suspend fun modifyRoom(request: ReqModifyRoom): ResCommon = TODO("Not yet implemented")

  override suspend fun addRoomRobot(request: ReqAddRoomRobot): ResCommon = TODO("Not yet implemented")

  override suspend fun matchGame(request: ReqJoinMatchQueue): ResCommon = TODO("Not yet implemented")

  override suspend fun cancelMatch(request: ReqCancelMatchQueue): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchAccountInfo(request: ReqAccountInfo): ResAccountInfo = TODO("Not yet implemented")

  override suspend fun changeAvatar(request: ReqChangeAvatar): ResCommon = TODO("Not yet implemented")

  override suspend fun receiveVersionReward(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchAccountStatisticInfo(request: ReqAccountStatisticInfo): ResAccountStatisticInfo = TODO("Not yet implemented")

  override suspend fun fetchAccountChallengeRankInfo(request: ReqAccountInfo): ResAccountChallengeRankInfo = TODO("Not yet implemented")

  override suspend fun fetchAccountCharacterInfo(request: ReqCommon): ResAccountCharacterInfo = TODO("Not yet implemented")

  override suspend fun shopPurchase(request: ReqShopPurchase): ResShopPurchase = TODO("Not yet implemented")

  override suspend fun fetchGameRecord(request: ReqGameRecord): ResGameRecord = TODO("Not yet implemented")

  override suspend fun readGameRecord(request: ReqGameRecord): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchGameRecordList(request: ReqGameRecordList): ResGameRecordList = TODO("Not yet implemented")

  override suspend fun fetchGameRecordListV2(request: ReqGameRecordListV2): ResGameRecordListV2 = TODO("Not yet implemented")

  override suspend fun fetchNextGameRecordList(request: ReqNextGameRecordList): ResNextGameRecordList = TODO("Not yet implemented")

  override suspend fun fetchCollectedGameRecordList(request: ReqCommon): ResCollectedGameRecordList = TODO("Not yet implemented")

  override suspend fun fetchGameRecordsDetail(request: ReqGameRecordsDetail): ResGameRecordsDetail = TODO("Not yet implemented")

  override suspend fun fetchGameRecordsDetailV2(request: ReqGameRecordsDetailV2): ResGameRecordsDetailV2 = TODO("Not yet implemented")

  override suspend fun addCollectedGameRecord(request: ReqAddCollectedGameRecord): ResAddCollectedGameRecord = TODO("Not yet implemented")

  override suspend fun removeCollectedGameRecord(request: ReqRemoveCollectedGameRecord): ResRemoveCollectedGameRecord =
    TODO("Not yet implemented")

  override suspend fun changeCollectedGameRecordRemarks(request: ReqChangeCollectedGameRecordRemarks): ResChangeCollectedGameRecordRemarks =
    TODO("Not yet implemented")

  override suspend fun fetchLevelLeaderboard(request: ReqLevelLeaderboard): ResLevelLeaderboard = TODO("Not yet implemented")

  override suspend fun fetchChallengeLeaderboard(request: ReqChallangeLeaderboard): ResChallengeLeaderboard = TODO("Not yet implemented")

  override suspend fun fetchMutiChallengeLevel(request: ReqMutiChallengeLevel): ResMutiChallengeLevel = TODO("Not yet implemented")

  override suspend fun fetchMultiAccountBrief(request: ReqMultiAccountId): ResMultiAccountBrief = TODO("Not yet implemented")

  override suspend fun fetchFriendList(request: ReqCommon): ResFriendList = TODO("Not yet implemented")

  override suspend fun fetchFriendApplyList(request: ReqCommon): ResFriendApplyList = TODO("Not yet implemented")

  override suspend fun applyFriend(request: ReqApplyFriend): ResCommon = TODO("Not yet implemented")

  override suspend fun handleFriendApply(request: ReqHandleFriendApply): ResCommon = TODO("Not yet implemented")

  override suspend fun removeFriend(request: ReqRemoveFriend): ResCommon = TODO("Not yet implemented")

  override suspend fun searchAccountById(request: ReqSearchAccountById): ResSearchAccountById = TODO("Not yet implemented")

  override suspend fun searchAccountByPattern(request: ReqSearchAccountByPattern): ResSearchAccountByPattern = TODO("Not yet implemented")

  override suspend fun fetchAccountState(request: ReqAccountList): ResAccountStates = TODO("Not yet implemented")

  override suspend fun fetchBagInfo(request: ReqCommon): ResBagInfo = TODO("Not yet implemented")

  override suspend fun useBagItem(request: ReqUseBagItem): ResCommon = TODO("Not yet implemented")

  override suspend fun openManualItem(request: ReqOpenManualItem): ResCommon = TODO("Not yet implemented")

  override suspend fun openRandomRewardItem(request: ReqOpenRandomRewardItem): ResOpenRandomRewardItem = TODO("Not yet implemented")

  override suspend fun openAllRewardItem(request: ReqOpenAllRewardItem): ResOpenAllRewardItem = TODO("Not yet implemented")

  override suspend fun composeShard(request: ReqComposeShard): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchAnnouncement(request: ReqFetchAnnouncement): ResAnnouncement = TODO("Not yet implemented")

  override suspend fun readAnnouncement(request: ReqReadAnnouncement): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchMailInfo(request: ReqCommon): ResMailInfo = TODO("Not yet implemented")

  override suspend fun readMail(request: ReqReadMail): ResCommon = TODO("Not yet implemented")

  override suspend fun deleteMail(request: ReqDeleteMail): ResCommon = TODO("Not yet implemented")

  override suspend fun takeAttachmentFromMail(request: ReqTakeAttachment): ResCommon = TODO("Not yet implemented")

  override suspend fun receiveAchievementReward(request: ReqReceiveAchievementReward): ResReceiveAchievementReward =
    TODO("Not yet implemented")

  override suspend fun receiveAchievementGroupReward(request: ReqReceiveAchievementGroupReward): ResReceiveAchievementGroupReward =
    TODO("Not yet implemented")

  override suspend fun fetchAchievementRate(request: ReqCommon): ResFetchAchievementRate = TODO("Not yet implemented")

  override suspend fun fetchAchievement(request: ReqCommon): ResAchievement = TODO("Not yet implemented")

  override suspend fun buyShiLian(request: ReqBuyShiLian): ResCommon = TODO("Not yet implemented")

  override suspend fun matchShiLian(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun goNextShiLian(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun updateClientValue(request: ReqUpdateClientValue): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchClientValue(request: ReqCommon): ResClientValue = TODO("Not yet implemented")

  override suspend fun clientMessage(request: ReqClientMessage): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchCurrentMatchInfo(request: ReqCurrentMatchInfo): ResCurrentMatchInfo = TODO("Not yet implemented")

  override suspend fun userComplain(request: ReqUserComplain): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchReviveCoinInfo(request: ReqCommon): ResReviveCoinInfo = TODO("Not yet implemented")

  override suspend fun gainReviveCoin(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchDailyTask(request: ReqCommon): ResDailyTask = TODO("Not yet implemented")

  override suspend fun refreshDailyTask(request: ReqRefreshDailyTask): ResRefreshDailyTask = TODO("Not yet implemented")

  override suspend fun useGiftCode(request: ReqUseGiftCode): ResUseGiftCode = TODO("Not yet implemented")

  override suspend fun useSpecialGiftCode(request: ReqUseGiftCode): ResUseSpecialGiftCode = TODO("Not yet implemented")

  override suspend fun fetchTitleList(request: ReqCommon): ResTitleList = TODO("Not yet implemented")

  override suspend fun useTitle(request: ReqUseTitle): ResCommon = TODO("Not yet implemented")

  override suspend fun sendClientMessage(request: ReqSendClientMessage): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchGameLiveInfo(request: ReqGameLiveInfo): ResGameLiveInfo = TODO("Not yet implemented")

  override suspend fun fetchGameLiveLeftSegment(request: ReqGameLiveLeftSegment): ResGameLiveLeftSegment = TODO("Not yet implemented")

  override suspend fun fetchGameLiveList(request: ReqGameLiveList): ResGameLiveList = TODO("Not yet implemented")

  override suspend fun fetchCommentSetting(request: ReqCommon): ResCommentSetting = TODO("Not yet implemented")

  override suspend fun updateCommentSetting(request: ReqUpdateCommentSetting): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchCommentList(request: ReqFetchCommentList): ResFetchCommentList = TODO("Not yet implemented")

  override suspend fun fetchCommentContent(request: ReqFetchCommentContent): ResFetchCommentContent = TODO("Not yet implemented")

  override suspend fun leaveComment(request: ReqLeaveComment): ResCommon = TODO("Not yet implemented")

  override suspend fun deleteComment(request: ReqDeleteComment): ResCommon = TODO("Not yet implemented")

  override suspend fun updateReadComment(request: ReqUpdateReadComment): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchRollingNotice(request: ReqFetchRollingNotice): ResFetchRollingNotice = TODO("Not yet implemented")

  override suspend fun fetchMaintainNotice(request: ReqCommon): ResFetchMaintainNotice = TODO("Not yet implemented")

  override suspend fun fetchServerTime(request: ReqCommon): ResServerTime = TODO("Not yet implemented")

  override suspend fun fetchPlatformProducts(request: ReqPlatformBillingProducts): ResPlatformBillingProducts = TODO("Not yet implemented")

  override suspend fun fetchRandomCharacter(request: ReqCommon): ResRandomCharacter = TODO("Not yet implemented")

  override suspend fun setRandomCharacter(request: ReqRandomCharacter): ResCommon = TODO("Not yet implemented")

  override suspend fun cancelGooglePlayOrder(request: ReqCancelGooglePlayOrder): ResCommon = TODO("Not yet implemented")

  override suspend fun openChest(request: ReqOpenChest): ResOpenChest = TODO("Not yet implemented")

  override suspend fun buyFromChestShop(request: ReqBuyFromChestShop): ResBuyFromChestShop = TODO("Not yet implemented")

  override suspend fun fetchDailySignInInfo(request: ReqCommon): ResDailySignInInfo = TODO("Not yet implemented")

  override suspend fun doDailySignIn(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun doActivitySignIn(request: ReqDoActivitySignIn): ResDoActivitySignIn = TODO("Not yet implemented")

  override suspend fun fetchCharacterInfo(request: ReqCommon): ResCharacterInfo = TODO("Not yet implemented")

  override suspend fun updateCharacterSort(request: ReqUpdateCharacterSort): ResCommon = TODO("Not yet implemented")

  override suspend fun changeMainCharacter(request: ReqChangeMainCharacter): ResCommon = TODO("Not yet implemented")

  override suspend fun changeCharacterSkin(request: ReqChangeCharacterSkin): ResCommon = TODO("Not yet implemented")

  override suspend fun changeCharacterView(request: ReqChangeCharacterView): ResCommon = TODO("Not yet implemented")

  override suspend fun setHiddenCharacter(request: ReqSetHiddenCharacter): ResSetHiddenCharacter = TODO("Not yet implemented")

  override suspend fun sendGiftToCharacter(request: ReqSendGiftToCharacter): ResSendGiftToCharacter = TODO("Not yet implemented")

  override suspend fun sellItem(request: ReqSellItem): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchCommonView(request: ReqCommon): ResCommonView = TODO("Not yet implemented")

  override suspend fun changeCommonView(request: ReqChangeCommonView): ResCommon = TODO("Not yet implemented")

  override suspend fun saveCommonViews(request: ReqSaveCommonViews): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchCommonViews(request: ReqCommonViews): ResCommonViews = TODO("Not yet implemented")

  override suspend fun fetchAllCommonViews(request: ReqCommon): ResAllcommonViews = TODO("Not yet implemented")

  override suspend fun useCommonView(request: ReqUseCommonView): ResCommon = TODO("Not yet implemented")

  override suspend fun upgradeCharacter(request: ReqUpgradeCharacter): ResUpgradeCharacter = TODO("Not yet implemented")

  override suspend fun addFinishedEnding(request: ReqFinishedEnding): ResCommon = TODO("Not yet implemented")

  override suspend fun receiveEndingReward(request: ReqFinishedEnding): ResCommon = TODO("Not yet implemented")

  override suspend fun gameMasterCommand(request: ReqGMCommand): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchShopInfo(request: ReqCommon): ResShopInfo = TODO("Not yet implemented")

  override suspend fun buyFromShop(request: ReqBuyFromShop): ResBuyFromShop = TODO("Not yet implemented")

  override suspend fun buyFromZHP(request: ReqBuyFromZHP): ResCommon = TODO("Not yet implemented")

  override suspend fun refreshZHPShop(request: ReqReshZHPShop): ResRefreshZHPShop = TODO("Not yet implemented")

  override suspend fun fetchMonthTicketInfo(request: ReqCommon): ResMonthTicketInfo = TODO("Not yet implemented")

  override suspend fun payMonthTicket(request: ReqCommon): ResPayMonthTicket = TODO("Not yet implemented")

  override suspend fun exchangeCurrency(request: ReqExchangeCurrency): ResCommon = TODO("Not yet implemented")

  override suspend fun exchangeChestStone(request: ReqExchangeCurrency): ResCommon = TODO("Not yet implemented")

  override suspend fun exchangeDiamond(request: ReqExchangeCurrency): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchServerSettings(request: ReqCommon): ResServerSettings = TODO("Not yet implemented")

  override suspend fun fetchAccountSettings(request: ReqCommon): ResAccountSettings = TODO("Not yet implemented")

  override suspend fun updateAccountSettings(request: ReqUpdateAccountSettings): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchModNicknameTime(request: ReqCommon): ResModNicknameTime = TODO("Not yet implemented")

  override suspend fun createWechatNativeOrder(request: ReqCreateWechatNativeOrder): ResCreateWechatNativeOrder =
    TODO("Not yet implemented")

  override suspend fun createWechatAppOrder(request: ReqCreateWechatAppOrder): ResCreateWechatAppOrder = TODO("Not yet implemented")

  override suspend fun createAlipayOrder(request: ReqCreateAlipayOrder): ResCreateAlipayOrder = TODO("Not yet implemented")

  override suspend fun createAlipayScanOrder(request: ReqCreateAlipayScanOrder): ResCreateAlipayScanOrder = TODO("Not yet implemented")

  override suspend fun createAlipayAppOrder(request: ReqCreateAlipayAppOrder): ResCreateAlipayAppOrder = TODO("Not yet implemented")

  override suspend fun createJPCreditCardOrder(request: ReqCreateJPCreditCardOrder): ResCreateJPCreditCardOrder =
    TODO("Not yet implemented")

  override suspend fun createJPPaypalOrder(request: ReqCreateJPPaypalOrder): ResCreateJPPaypalOrder = TODO("Not yet implemented")

  override suspend fun createJPAuOrder(request: ReqCreateJPAuOrder): ResCreateJPAuOrder = TODO("Not yet implemented")

  override suspend fun createJPDocomoOrder(request: ReqCreateJPDocomoOrder): ResCreateJPDocomoOrder = TODO("Not yet implemented")

  override suspend fun createJPWebMoneyOrder(request: ReqCreateJPWebMoneyOrder): ResCreateJPWebMoneyOrder = TODO("Not yet implemented")

  override suspend fun createJPSoftbankOrder(request: ReqCreateJPSoftbankOrder): ResCreateJPSoftbankOrder = TODO("Not yet implemented")

  override suspend fun createJPPayPayOrder(request: ReqCreateJPPayPayOrder): ResCreateJPPayPayOrder = TODO("Not yet implemented")

  override suspend fun fetchJPCommonCreditCardOrder(request: ReqFetchJPCommonCreditCardOrder): ResFetchJPCommonCreditCardOrder =
    TODO("Not yet implemented")

  override suspend fun createJPGMOOrder(request: ReqCreateJPGMOOrder): ResCreateJPGMOOrder = TODO("Not yet implemented")

  override suspend fun createENPaypalOrder(request: ReqCreateENPaypalOrder): ResCreateENPaypalOrder = TODO("Not yet implemented")

  override suspend fun createENMasterCardOrder(request: ReqCreateENMasterCardOrder): ResCreateENMasterCardOrder =
    TODO("Not yet implemented")

  override suspend fun createENVisaOrder(request: ReqCreateENVisaOrder): ResCreateENVisaOrder = TODO("Not yet implemented")

  override suspend fun createENJCBOrder(request: ReqCreateENJCBOrder): ResCreateENJCBOrder = TODO("Not yet implemented")

  override suspend fun createENAlipayOrder(request: ReqCreateENAlipayOrder): ResCreateENAlipayOrder = TODO("Not yet implemented")

  override suspend fun createKRPaypalOrder(request: ReqCreateKRPaypalOrder): ResCreateKRPaypalOrder = TODO("Not yet implemented")

  override suspend fun createKRMasterCardOrder(request: ReqCreateKRMasterCardOrder): ResCreateKRMasterCardOrder =
    TODO("Not yet implemented")

  override suspend fun createKRVisaOrder(request: ReqCreateKRVisaOrder): ResCreateKRVisaOrder = TODO("Not yet implemented")

  override suspend fun createKRJCBOrder(request: ReqCreateKRJCBOrder): ResCreateKRJCBOrder = TODO("Not yet implemented")

  override suspend fun createKRAlipayOrder(request: ReqCreateKRAlipayOrder): ResCreateKRAlipayOrder = TODO("Not yet implemented")

  override suspend fun createDMMOrder(request: ReqCreateDMMOrder): ResCreateDmmOrder = TODO("Not yet implemented")

  override suspend fun createIAPOrder(request: ReqCreateIAPOrder): ResCreateIAPOrder = TODO("Not yet implemented")

  override suspend fun createSteamOrder(request: ReqCreateSteamOrder): ResCreateSteamOrder = TODO("Not yet implemented")

  override suspend fun verifySteamOrder(request: ReqVerifySteamOrder): ResCommon = TODO("Not yet implemented")

  override suspend fun createMyCardAndroidOrder(request: ReqCreateMyCardOrder): ResCreateMyCardOrder = TODO("Not yet implemented")

  override suspend fun createMyCardWebOrder(request: ReqCreateMyCardOrder): ResCreateMyCardOrder = TODO("Not yet implemented")

  override suspend fun createPaypalOrder(request: ReqCreatePaypalOrder): ResCreatePaypalOrder = TODO("Not yet implemented")

  override suspend fun createXsollaOrder(request: ReqCreateXsollaOrder): ResCreateXsollaOrder = TODO("Not yet implemented")

  override suspend fun verifyMyCardOrder(request: ReqVerifyMyCardOrder): ResCommon = TODO("Not yet implemented")

  override suspend fun verificationIAPOrder(request: ReqVerificationIAPOrder): ResVerificationIAPOrder = TODO("Not yet implemented")

  override suspend fun createYostarSDKOrder(request: ReqCreateYostarOrder): ResCreateYostarOrder = TODO("Not yet implemented")

  override suspend fun createBillingOrder(request: ReqCreateBillingOrder): ResCreateBillingOrder = TODO("Not yet implemented")

  override suspend fun solveGooglePlayOrder(request: ReqSolveGooglePlayOrder): ResCommon = TODO("Not yet implemented")

  override suspend fun solveGooglePayOrderV3(request: ReqSolveGooglePlayOrderV3): ResCommon = TODO("Not yet implemented")

  override suspend fun deliverAA32Order(request: ReqDeliverAA32Order): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchMisc(request: ReqCommon): ResMisc = TODO("Not yet implemented")

  override suspend fun modifySignature(request: ReqModifySignature): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchIDCardInfo(request: ReqCommon): ResIDCardInfo = TODO("Not yet implemented")

  override suspend fun updateIDCardInfo(request: ReqUpdateIDCardInfo): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchVipReward(request: ReqCommon): ResVipReward = TODO("Not yet implemented")

  override suspend fun gainVipReward(request: ReqGainVipReward): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchRefundOrder(request: ReqCommon): ResFetchRefundOrder = TODO("Not yet implemented")

  override suspend fun fetchCustomizedContestList(request: ReqFetchCustomizedContestList): ResFetchCustomizedContestList =
    TODO("Not yet implemented")

  override suspend fun fetchCustomizedContestAuthInfo(request: ReqFetchCustomizedContestAuthInfo): ResFetchCustomizedContestAuthInfo =
    TODO("Not yet implemented")

  override suspend fun enterCustomizedContest(request: ReqEnterCustomizedContest): ResEnterCustomizedContest = TODO("Not yet implemented")

  override suspend fun leaveCustomizedContest(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchCustomizedContestOnlineInfo(request: ReqFetchCustomizedContestOnlineInfo): ResFetchCustomizedContestOnlineInfo =
    TODO("Not yet implemented")

  override suspend fun fetchCustomizedContestByContestId(
    request: ReqFetchCustomizedContestByContestId,
  ): ResFetchCustomizedContestByContestId = TODO("Not yet implemented")

  override suspend fun signupCustomizedContest(request: ReqSignupCustomizedContest): ResSignupCustomizedContest =
    TODO("Not yet implemented")

  override suspend fun startCustomizedContest(request: ReqStartCustomizedContest): ResCommon = TODO("Not yet implemented")

  override suspend fun stopCustomizedContest(request: ReqStopCustomizedContest): ResCommon = TODO("Not yet implemented")

  override suspend fun joinCustomizedContestChatRoom(request: ReqJoinCustomizedContestChatRoom): ResJoinCustomizedContestChatRoom =
    TODO("Not yet implemented")

  override suspend fun leaveCustomizedContestChatRoom(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun sayChatMessage(request: ReqSayChatMessage): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchCustomizedContestGameRecords(
    request: ReqFetchCustomizedContestGameRecords,
  ): ResFetchCustomizedContestGameRecords = TODO("Not yet implemented")

  override suspend fun fetchCustomizedContestGameLiveList(
    request: ReqFetchCustomizedContestGameLiveList,
  ): ResFetchCustomizedContestGameLiveList = TODO("Not yet implemented")

  override suspend fun followCustomizedContest(request: ReqTargetCustomizedContest): ResCommon = TODO("Not yet implemented")

  override suspend fun unfollowCustomizedContest(request: ReqTargetCustomizedContest): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchActivityList(request: ReqCommon): ResActivityList = TODO("Not yet implemented")

  override suspend fun fetchAccountActivityData(request: ReqCommon): ResAccountActivityData = TODO("Not yet implemented")

  override suspend fun exchangeActivityItem(request: ReqExchangeActivityItem): ResExchangeActivityItem = TODO("Not yet implemented")

  override suspend fun completeActivityTask(request: ReqCompleteActivityTask): ResCommon = TODO("Not yet implemented")

  override suspend fun completeActivityTaskBatch(request: ReqCompleteActivityTaskBatch): ResCommon = TODO("Not yet implemented")

  override suspend fun completeActivityFlipTask(request: ReqCompleteActivityTask): ResCommon = TODO("Not yet implemented")

  override suspend fun completePeriodActivityTask(request: ReqCompleteActivityTask): ResCommon = TODO("Not yet implemented")

  override suspend fun completePeriodActivityTaskBatch(request: ReqCompletePeriodActivityTaskBatch): ResCommon = TODO("Not yet implemented")

  override suspend fun completeRandomActivityTask(request: ReqCompleteActivityTask): ResCommon = TODO("Not yet implemented")

  override suspend fun completeRandomActivityTaskBatch(request: ReqCompleteActivityTaskBatch): ResCommon = TODO("Not yet implemented")

  override suspend fun receiveActivityFlipTask(request: ReqReceiveActivityFlipTask): ResReceiveActivityFlipTask =
    TODO("Not yet implemented")

  override suspend fun completeSegmentTaskReward(request: ReqCompleteSegmentTaskReward): ResCompleteSegmentTaskReward =
    TODO("Not yet implemented")

  override suspend fun fetchActivityFlipInfo(request: ReqFetchActivityFlipInfo): ResFetchActivityFlipInfo = TODO("Not yet implemented")

  override suspend fun gainAccumulatedPointActivityReward(request: ReqGainAccumulatedPointActivityReward): ResCommon =
    TODO("Not yet implemented")

  override suspend fun gainMultiPointActivityReward(request: ReqGainMultiPointActivityReward): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchRankPointLeaderboard(request: ReqFetchRankPointLeaderboard): ResFetchRankPointLeaderboard =
    TODO("Not yet implemented")

  override suspend fun gainRankPointReward(request: ReqGainRankPointReward): ResCommon = TODO("Not yet implemented")

  override suspend fun richmanActivityNextMove(request: ReqRichmanNextMove): ResRichmanNextMove = TODO("Not yet implemented")

  override suspend fun richmanAcitivitySpecialMove(request: ReqRichmanSpecialMove): ResRichmanNextMove = TODO("Not yet implemented")

  override suspend fun richmanActivityChestInfo(request: ReqRichmanChestInfo): ResRichmanChestInfo = TODO("Not yet implemented")

  override suspend fun createGameObserveAuth(request: ReqCreateGameObserveAuth): ResCreateGameObserveAuth = TODO("Not yet implemented")

  override suspend fun refreshGameObserveAuth(request: ReqRefreshGameObserveAuth): ResRefreshGameObserveAuth = TODO("Not yet implemented")

  override suspend fun fetchActivityBuff(request: ReqCommon): ResActivityBuff = TODO("Not yet implemented")

  override suspend fun upgradeActivityBuff(request: ReqUpgradeActivityBuff): ResActivityBuff = TODO("Not yet implemented")

  override suspend fun upgradeActivityLevel(request: ReqUpgradeActivityLevel): ResUpgradeActivityLevel = TODO("Not yet implemented")

  override suspend fun receiveUpgradeActivityReward(request: ReqReceiveUpgradeActivityReward): ResReceiveUpgradeActivityReward =
    TODO("Not yet implemented")

  override suspend fun upgradeChallenge(request: ReqCommon): ResUpgradeChallenge = TODO("Not yet implemented")

  override suspend fun refreshChallenge(request: ReqCommon): ResRefreshChallenge = TODO("Not yet implemented")

  override suspend fun fetchChallengeInfo(request: ReqCommon): ResFetchChallengeInfo = TODO("Not yet implemented")

  override suspend fun forceCompleteChallengeTask(request: ReqForceCompleteChallengeTask): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchChallengeSeason(request: ReqCommon): ResChallengeSeasonInfo = TODO("Not yet implemented")

  override suspend fun receiveChallengeRankReward(request: ReqReceiveChallengeRankReward): ResReceiveChallengeRankReward =
    TODO("Not yet implemented")

  override suspend fun fetchABMatchInfo(request: ReqCommon): ResFetchABMatch = TODO("Not yet implemented")

  override suspend fun buyInABMatch(request: ReqBuyInABMatch): ResCommon = TODO("Not yet implemented")

  override suspend fun receiveABMatchReward(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun quitABMatch(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun startUnifiedMatch(request: ReqStartUnifiedMatch): ResCommon = TODO("Not yet implemented")

  override suspend fun cancelUnifiedMatch(request: ReqCancelUnifiedMatch): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchGamePointRank(request: ReqGamePointRank): ResGamePointRank = TODO("Not yet implemented")

  override suspend fun fetchSelfGamePointRank(request: ReqGamePointRank): ResFetchSelfGamePointRank = TODO("Not yet implemented")

  override suspend fun readSNS(request: ReqReadSNS): ResReadSNS = TODO("Not yet implemented")

  override suspend fun replySNS(request: ReqReplySNS): ResReplySNS = TODO("Not yet implemented")

  override suspend fun likeSNS(request: ReqLikeSNS): ResLikeSNS = TODO("Not yet implemented")

  override suspend fun digMine(request: ReqDigMine): ResDigMine = TODO("Not yet implemented")

  override suspend fun fetchLastPrivacy(request: ReqFetchLastPrivacy): ResFetchLastPrivacy = TODO("Not yet implemented")

  override suspend fun checkPrivacy(request: ReqCheckPrivacy): ResCommon = TODO("Not yet implemented")

  override suspend fun responseCaptcha(request: ReqResponseCaptcha): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchRPGBattleHistory(request: ReqFetchRPGBattleHistory): ResFetchRPGBattleHistory = TODO("Not yet implemented")

  override suspend fun fetchRPGBattleHistoryV2(request: ReqFetchRPGBattleHistory): ResFetchRPGBattleHistoryV2 = TODO("Not yet implemented")

  override suspend fun receiveRPGRewards(request: ReqReceiveRPGRewards): ResReceiveRPGRewards = TODO("Not yet implemented")

  override suspend fun receiveRPGReward(request: ReqReceiveRPGReward): ResReceiveRPGRewards = TODO("Not yet implemented")

  override suspend fun buyArenaTicket(request: ReqBuyArenaTicket): ResCommon = TODO("Not yet implemented")

  override suspend fun enterArena(request: ReqEnterArena): ResCommon = TODO("Not yet implemented")

  override suspend fun receiveArenaReward(request: ReqArenaReward): ResArenaReward = TODO("Not yet implemented")

  override suspend fun fetchOBToken(request: ReqFetchOBToken): ResFetchOBToken = TODO("Not yet implemented")

  override suspend fun receiveCharacterRewards(request: ReqReceiveCharacterRewards): ResReceiveCharacterRewards =
    TODO("Not yet implemented")

  override suspend fun feedActivityFeed(request: ReqFeedActivityFeed): ResFeedActivityFeed = TODO("Not yet implemented")

  override suspend fun sendActivityGiftToFriend(request: ReqSendActivityGiftToFriend): ResSendActivityGiftToFriend =
    TODO("Not yet implemented")

  override suspend fun receiveActivityGift(request: ReqReceiveActivityGift): ResCommon = TODO("Not yet implemented")

  override suspend fun receiveAllActivityGift(request: ReqReceiveAllActivityGift): ResReceiveAllActivityGift = TODO("Not yet implemented")

  override suspend fun fetchFriendGiftActivityData(request: ReqFetchFriendGiftActivityData): ResFetchFriendGiftActivityData =
    TODO("Not yet implemented")

  override suspend fun openPreChestItem(request: ReqOpenPreChestItem): ResOpenPreChestItem = TODO("Not yet implemented")

  override suspend fun fetchVoteActivity(request: ReqFetchVoteActivity): ResFetchVoteActivity = TODO("Not yet implemented")

  override suspend fun voteActivity(request: ReqVoteActivity): ResVoteActivity = TODO("Not yet implemented")

  override suspend fun unlockActivitySpot(request: ReqUnlockActivitySpot): ResCommon = TODO("Not yet implemented")

  override suspend fun unlockActivitySpotEnding(request: ReqUnlockActivitySpotEnding): ResCommon = TODO("Not yet implemented")

  override suspend fun receiveActivitySpotReward(request: ReqReceiveActivitySpotReward): ResReceiveActivitySpotReward =
    TODO("Not yet implemented")

  override suspend fun deleteAccount(request: ReqCommon): ResDeleteAccount = TODO("Not yet implemented")

  override suspend fun cancelDeleteAccount(request: ReqCommon): ResCommon = TODO("Not yet implemented")

  override suspend fun logReport(request: ReqLogReport): ResCommon = TODO("Not yet implemented")

  override suspend fun bindOauth2(request: ReqBindOauth2): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchOauth2Info(request: ReqFetchOauth2): ResFetchOauth2 = TODO("Not yet implemented")

  override suspend fun setLoadingImage(request: ReqSetLoadingImage): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchShopInterval(request: ReqCommon): ResFetchShopInterval = TODO("Not yet implemented")

  override suspend fun fetchActivityInterval(request: ReqCommon): ResFetchActivityInterval = TODO("Not yet implemented")

  override suspend fun fetchRecentFriend(request: ReqCommon): ResFetchrecentFriend = TODO("Not yet implemented")

  override suspend fun openGacha(request: ReqOpenGacha): ResOpenGacha = TODO("Not yet implemented")

  override suspend fun taskRequest(request: ReqTaskRequest): ResCommon = TODO("Not yet implemented")

  override suspend fun simulationActivityTrain(request: ReqSimulationActivityTrain): ResSimulationActivityTrain =
    TODO("Not yet implemented")

  override suspend fun fetchSimulationGameRecord(request: ReqFetchSimulationGameRecord): ResFetchSimulationGameRecord =
    TODO("Not yet implemented")

  override suspend fun startSimulationActivityGame(request: ReqStartSimulationActivityGame): ResStartSimulationActivityGame =
    TODO("Not yet implemented")

  override suspend fun fetchSimulationGameRank(request: ReqFetchSimulationGameRank): ResFetchSimulationGameRank =
    TODO("Not yet implemented")

  override suspend fun generateCombiningCraft(request: ReqGenerateCombiningCraft): ResGenerateCombiningCraft = TODO("Not yet implemented")

  override suspend fun moveCombiningCraft(request: ReqMoveCombiningCraft): ResMoveCombiningCraft = TODO("Not yet implemented")

  override suspend fun combiningRecycleCraft(request: ReqCombiningRecycleCraft): ResCombiningRecycleCraft = TODO("Not yet implemented")

  override suspend fun recoverCombiningRecycle(request: ReqRecoverCombiningRecycle): ResRecoverCombiningRecycle =
    TODO("Not yet implemented")

  override suspend fun finishCombiningOrder(request: ReqFinishCombiningOrder): ResFinishCombiningOrder = TODO("Not yet implemented")

  override suspend fun upgradeVillageBuilding(request: ReqUpgradeVillageBuilding): ResCommon = TODO("Not yet implemented")

  override suspend fun receiveVillageBuildingReward(request: ReqReceiveVillageBuildingReward): ResReceiveVillageBuildingReward =
    TODO("Not yet implemented")

  override suspend fun startVillageTrip(request: ReqStartVillageTrip): ResCommon = TODO("Not yet implemented")

  override suspend fun receiveVillageTripReward(request: ReqReceiveVillageTripReward): ResReceiveVillageTripReward =
    TODO("Not yet implemented")

  override suspend fun completeVillageTask(request: ReqCompleteVillageTask): ResCompleteVillageTask = TODO("Not yet implemented")

  override suspend fun getFriendVillageData(request: ReqGetFriendVillageData): ResGetFriendVillageData = TODO("Not yet implemented")

  override suspend fun setVillageWorker(request: ReqSetVillageWorker): ResSetVillageWorker = TODO("Not yet implemented")

  override suspend fun nextRoundVillage(request: ReqNextRoundVillage): ResNextRoundVillage = TODO("Not yet implemented")

  override suspend fun resolveFestivalActivityProposal(request: ReqResolveFestivalActivityProposal): ResResolveFestivalActivityProposal =
    TODO("Not yet implemented")

  override suspend fun resolveFestivalActivityEvent(request: ReqResolveFestivalActivityEvent): ResResolveFestivalActivityEvent =
    TODO("Not yet implemented")

  override suspend fun buyFestivalProposal(request: ReqBuyFestivalProposal): ResBuyFestivalProposal = TODO("Not yet implemented")

  override suspend fun islandActivityMove(request: ReqIslandActivityMove): ResCommon = TODO("Not yet implemented")

  override suspend fun islandActivityBuy(request: ReqIslandActivityBuy): ResCommon = TODO("Not yet implemented")

  override suspend fun islandActivitySell(request: ReqIslandActivitySell): ResCommon = TODO("Not yet implemented")

  override suspend fun islandActivityTidyBag(request: ReqIslandActivityTidyBag): ResCommon = TODO("Not yet implemented")

  override suspend fun islandActivityUnlockBagGrid(request: ReqIslandActivityUnlockBagGrid): ResCommon = TODO("Not yet implemented")

  override suspend fun createCustomizedContest(request: ReqCreateCustomizedContest): ResCreateCustomizedContest =
    TODO("Not yet implemented")

  override suspend fun fetchManagerCustomizedContestList(
    request: ReqFetchmanagerCustomizedContestList,
  ): ResFetchManagerCustomizedContestList = TODO("Not yet implemented")

  override suspend fun fetchManagerCustomizedContest(request: ReqFetchManagerCustomizedContest): ResFetchManagerCustomizedContest =
    TODO("Not yet implemented")

  override suspend fun updateManagerCustomizedContest(request: ReqUpdateManagerCustomizedContest): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchContestPlayerRank(request: ReqFetchContestPlayerRank): ResFetchContestPlayerRank = TODO("Not yet implemented")

  override suspend fun fetchReadyPlayerList(request: ReqFetchReadyPlayerList): ResFetchReadyPlayerList = TODO("Not yet implemented")

  override suspend fun createGamePlan(request: ReqCreateGamePlan): ResCommon = TODO("Not yet implemented")

  override suspend fun generateContestManagerLoginCode(request: ReqCommon): ResGenerateContestManagerLoginCode = TODO("Not yet implemented")

  override suspend fun amuletActivityFetchInfo(request: ReqAmuletActivityFetchInfo): ResAmuletActivityFetchInfo =
    TODO("Not yet implemented")

  override suspend fun amuletActivityFetchBrief(request: ReqAmuletActivityFetchBrief): ResAmuletActivityFetchBrief =
    TODO("Not yet implemented")

  override suspend fun amuletActivityStartGame(request: ReqAmuletActivityStartGame): ResAmuletActivityStartGame =
    TODO("Not yet implemented")

  override suspend fun amuletActivityOperate(request: ReqAmuletActivityOperate): ResAmuletActivityOperate = TODO("Not yet implemented")

  override suspend fun amuletActivityChangeHands(request: ReqAmuletActivityChangeHands): ResAmuletActivityChangeHands =
    TODO("Not yet implemented")

  override suspend fun amuletActivityUpgrade(request: ReqAmuletActivityUpgrade): ResAmuletActivityUpgrade = TODO("Not yet implemented")

  override suspend fun amuletActivityBuy(request: ReqAmuletActivityBuy): ResAmuletActivityBuy = TODO("Not yet implemented")

  override suspend fun amuletActivitySelectPack(request: ReqAmuletActivitySelectPack): ResAmuletActivitySelectPack =
    TODO("Not yet implemented")

  override suspend fun amuletActivitySellEffect(request: ReqAmuletActivitySellEffect): ResAmuletActivitySellEffect =
    TODO("Not yet implemented")

  override suspend fun amuletActivityEffectSort(request: ReqAmuletActivityEffectSort): ResCommon = TODO("Not yet implemented")

  override suspend fun amuletActivityGiveup(request: ReqAmuletActivityGiveup): ResCommon = TODO("Not yet implemented")

  override suspend fun amuletActivityRefreshShop(request: ReqAmuletActivityRefreshShop): ResAmuletActivityRefreshShop =
    TODO("Not yet implemented")

  override suspend fun amuletActivitySelectFreeEffect(request: ReqAmuletActivitySelectFreeEffect): ResAmuletActivitySelectFreeEffect =
    TODO("Not yet implemented")

  override suspend fun amuletActivityUpgradeShopBuff(request: ReqAmuletActivityUpgradeShopBuff): ResAmuletActivityUpgradeShopBuff =
    TODO("Not yet implemented")

  override suspend fun amuletActivityEndShopping(request: ReqAmuletActivityEndShopping): ResAmuletActivityEndShopping =
    TODO("Not yet implemented")

  override suspend fun amuletActivitySetSkillLevel(request: ReqAmuletActivitySetSkillLevel): ResCommon = TODO("Not yet implemented")

  override suspend fun amuletActivityMaintainInfo(request: ReqCommon): ResAmuletActivityMaintainInfo = TODO("Not yet implemented")

  override suspend fun amuletActivitySelectRewardPack(request: ReqAmuletActivitySelectRewardPack): ResAmuletActivitySelectRewardPack =
    TODO("Not yet implemented")

  override suspend fun amuletActivityReceiveTaskReward(request: ReqAmuletActivityReceiveTaskReward): ResAmuletActivityReceiveTaskReward =
    TODO("Not yet implemented")

  override suspend fun storyActivityUnlock(request: ReqStoryActivityUnlock): ResCommon = TODO("Not yet implemented")

  override suspend fun storyActivityUnlockEnding(request: ReqStoryActivityUnlockEnding): ResCommon = TODO("Not yet implemented")

  override suspend fun storyActivityReceiveEndingReward(request: ReqStoryActivityReceiveEndingReward): ResStoryReward =
    TODO("Not yet implemented")

  override suspend fun storyActivityReceiveFinishReward(request: ReqStoryActivityReceiveFinishReward): ResStoryReward =
    TODO("Not yet implemented")

  override suspend fun storyActivityReceiveAllFinishReward(request: ReqStoryActivityReceiveAllFinishReward): ResStoryReward =
    TODO("Not yet implemented")

  override suspend fun storyActivityUnlockEndingAndReceive(
    request: ReqStoryActivityUnlockEndingAndReceive,
  ): ResStoryActivityUnlockEndingAndReceive = TODO("Not yet implemented")

  override suspend fun fetchActivityRank(request: ReqFetchActivityRank): ResFetchActivityRank = TODO("Not yet implemented")

  override suspend fun setVerifiedHidden(request: ReqSetVerifiedHidden): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchQuestionnaireList(request: ReqFetchQuestionnaireList): ResFetchQuestionnaireList = TODO("Not yet implemented")

  override suspend fun fetchQuestionnaireDetail(request: ReqFetchQuestionnaireDetail): ResFetchQuestionnaireDetail =
    TODO("Not yet implemented")

  override suspend fun submitQuestionnaire(request: ReqSubmitQuestionnaire): ResCommon = TODO("Not yet implemented")

  override suspend fun setFriendRoomRandomBotChar(request: ReqSetFriendRoomRandomBotChar): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchAccountGameHuRecords(request: ReqFetchAccountGameHuRecords): ResFetchAccountGameHuRecords =
    TODO("Not yet implemented")

  override suspend fun fetchAccountInfoExtra(request: ReqFetchAccountInfoExtra): ResFetchAccountInfoExtra = TODO("Not yet implemented")

  override suspend fun setAccountFavoriteHu(request: ReqSetAccountFavoriteHu): ResCommon = TODO("Not yet implemented")

  override suspend fun fetchSeerReport(request: ReqFetchSeerReport): ResFetchSeerReport = TODO("Not yet implemented")

  override suspend fun createSeerReport(request: ReqCreateSeerReport): ResCreateSeerReport = TODO("Not yet implemented")

  override suspend fun fetchSeerReportList(request: ReqCommon): ResFetchSeerReportList = TODO("Not yet implemented")

  override suspend fun fetchSeerInfo(request: ReqCommon): ResFetchSeerInfo = TODO("Not yet implemented")

  override suspend fun selectChestChooseUpActivity(request: ReqSelectChestChooseUp): ReqCommon = TODO("Not yet implemented")
}
