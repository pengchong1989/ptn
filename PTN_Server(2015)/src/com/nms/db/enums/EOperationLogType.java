package com.nms.db.enums;

import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysOperaType;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
/**
 * 操作日志记录
 * 字符 集
 * @author Administrator
 *
 */ 
public enum EOperationLogType { 
	
	SEGEMENTINSERT(1),SEGEMENTUPDATE(2),SEGEMENTDELETE(3),SEGEMENTSELECT(4),
	TUNNELINSERT(5),TUNNELUPDATE(6),TUNNELDELETE(7),TUNNELSELECT(8),
	LOGINSUCCESS(9),EXITSYSTEM(10),TUNNELROTATE(11),
	PWINSERT(12),PWUPDATE(13),PWDELETE(14),PWSELECT(15),PWVLAN(16),
	ELINEINSERT(17),ELINEUPDATE(18),ELINEDELETE(19),ELINESELECT(20),ADDRAPID(21),
	ETREEINSERT(22),ETREEUPDATE(23),ETREEDELETE(24),ETREESELECT(25),
	ELANINSERT(26),ELANUPDATE(27),ELANDELETE(28),ELANSELECT(29),
	CESINSERT(30),CESUPDATE(31),CESDELETE(32),CESSELECT(33),
	LOOPPROTECTINSERT(34),LOOPPROTECTUPDATE(35),LOOPPROTECTDELETE(36),LOOPPROTECTSELECT(37),
	SITESELECT(38),SITEUPDATE(39),ETHUPDATE(40),ETHSYNCHRO(41),
	PDHUPDATE(42),PDHSYNCHRO(43),SDHUPDATE(44),SDHSYNCHRO(45),SDHTIMESSLOTUPDATE(46),SDHTIMESSLOTSYNCHO(47)
	,ACINSERT(48),ACUPDATE(49),ACDELETE(50),ACSELECT(51),
	UNLOADUPDATE(52),UNLOADEXPORT(53),UNLOADINPORT(54)
	,TUNNELSYNCHRO(55),PWSYNCHRO(56),ELINESYNCHRO(57),ETREESYNCHRO(58),ELANSYNCHRO(59),CESSYNCHRO(60)
	,MCNUPDATE(61),MCNSYNCHRO(62),OSPFINSERT(63),OSPFUPDATE(64),OSPFDELETE(65),OSPFSYNCHRO(66)
	,FREQUENCYINFOUPDATE(67),FREQUENCYINSERT(68),FREQUENCYUPDATE(69),FREQUENCYDELETE(70)
	,FREQUENCYSYNCHRO(71),FREQUENCYROATE(72),TIMEMANAGEUPDATE(73),TIMEMANAGESYNCHRO(74),PORTINSERT(75)
	,PORTUPDATE(76),PORTDELETE(77),PORTSYNCHRO(78),PORTMAP(79),LOOPPTECTSYNCHRO(80),
	QOSMODEINSERT(81),QOSMODEUPDATE(82),QOSMODEDELETE(83),USERINSERT(84),USERUPDATE(85),USERDELETE(86),
	USERISLOCK(87),USERLOCK(88),USERLOGOUT(89),PERFORMANCEUPDATE(90),ALARMDESCUPDATE(91),SITEPORTSELECT(92),
	CLENTINSERT(93),CLIENTUPDATE(94),CLENTDELETE(95),SUBNETINSERT(96),SUBNETUPDATE(97),SUBNETDELETE(98),
	PMLIMITCONFIGUPDATE(99),SITELISTUPDATE(100),SITELISTDELETE(101),SITELISTSYNCHRO(102),SITELISTISSUED(103),
	SITELISTCURRECTTIME(104),SITELISTINITIALIZTION(105),SITELISTDOWNLODA(106),
	PORTLAGINSERT(107),PORTLAGUPDATE(108),PORTLAGDELETE(109),PORTLAGSYNCHRO(110),CURRENTPERFORMANCESELECT(111),
	HISPERFORMANCESELECT(112),PERFORMANCETASKINSERT(113),PERFORMANCETASKUPDATE(114),PERFORMANCETASKDELETE(115),
	PERFORMANCETASKFILTERSELECT(116),CURRENTALARMEXPORT(117),CURRENTALARMFILTERSELECT(118),
	HISALARMEXPORT(119),HISALARMFILTERSELECT(120),SITESTATISTICSEXPORT(121),SITEPHYCISEEXPORT(122),
	SLOTPORT(123),CARDEXPORT(124),ALARMEXPORT(125),LABELEXPORT(126),PORTEXPORT(127),SITEPHYPATHEXPORT(128),
	INPORTHISPERFORMANCE(129),EXPORTHISPERFORMANCE(130),FIELDINSERT(131),SITEINSERT(132),SITECOPY(133),
	FIELDDELETE(134),LINECLSYNCHRO(135),LINECLUPDATE(136),EXTERNALINSERT(137),EXTERNALUPDATE(138),EXTERNALSYNCHRO(139),
	SEGMENTOAMINSERT(140),SEGMENTOAMUPDATE(141),TUNNELOAMINSERT(142),TUNNELOAMUPDATE(143),
	PWOAMINSERT(144),PWOAMUPDATE(145),ELINEOAMINSERT(146),ELINEOAMUPDATE(147),ETHUNIOAMINSERT(148),
	ETHUNIOAMUPDATE(149),ETHERNETOAMINSERT(150),ETHERNETOAMUPDATE(151),ETHERNETOAMDELETE(152),MSPPROTECTINSERT(153),
	MSPPROTECTUPDATE(154),MSPPROTECTDELETE(155),MSPPROTECTSYNCHRO(156),DUALPROTECTINSERT(157),DUALPROTECTUPDATE(158),
	DUALPROTECTDELETE(159),DUALPROTECTSYNCHRO(160),DELETEROLE(161),INSERTROLE(162),UPDATEROLE(163),EXPORTPERFORMANCE(164),
	EXPMAPPINGINSERT(165),EXPMAPPINGSYNCHRO(166),VLANPRITOCOLORMAPPINGINSERT(167),VLANPRITOCOLORMAPPINGSYNCHRO(168),
	COSTOVLANPRIMAPPINGINSERT(169),COSTOVLANPRIMAPPINGSYNCHRO(170),CARDINSERT(171),CARDDELETE(172),REMOVELOGINLOG(173),
	REMOVEOPERATION(174),EXPORTOPERATELOG(175),EXPORTLOGINLOG(176),EXPMAPPINGDELETE(177),VLANPRITOCOLORMAPPINGDELETE(178),
	COSTOVLANPRIMAPPINGDELETE(179),UPDATEEXTERNALCLOCK(180),PROFESSEXPORT(181),CREATEGROUP(182),UPDATEGROUPID(183),
	DELETEGROUPID(184),DELETELOGINMANAGER(185),INSERTLOGINMANAGER(186),UPDATELOGINMANAGER(187),DELETENAMERULE(188),INSERTNAMERULE(189),
	UPDATENAMERULE(190),PORTPRI(191),PORTDISCARD(192),DELETEPORTVLAN(193),CONTAINPORTVLAN(194),CLEARCURRALARM(195), DUALROTATE(196),
	SECONDMACDELETE(197),PORT_2LAYER_ATTR(198),SECONDMACINSERT(199),SECONDMACUPDATE(200),
	//-------------------------------------------增加具体的操作错误的-------------------------------------------------------------------
	PASSWORDERROR(201),ALARMUSERPOWER(202),
	NOTFULLERROR(203),UPDATASELECTERROR(204),WARPPINGERROR(205),CESERROR(206),ELANERROR(207),EREEERROR(208),ELINEERROR(209),PWERROR(210),
	TUNNELERROR(211),SEGMENTERROR(212),LOGINERROR(213),USERERROR(214),IPERROR(215),LOGLOCK(216),SYSTEMLOCK(217),USERPASSERROR(218),SERVICEERROR(219),
	CONNECTIONMAX(220),CONNECTIONLAST(221),CARDCONFIGFAIL(222),PORTISUSER(223),ACISUSER(224),EXIST_GROUP(225),SITEEXISTAC(226),
	SITEEXITSEGMENT(227),SITEEXITTUNNEL(228),SITEEXITSUB(229),UNLOADOBJECT(230),DOWNLOAD(231),VLANPRIDOWNLOAD(232),COSTOEXPDOWNLOAD(233),UPORDOWNFILE(234),
	UPORDOWNFILESELECT(235),UPORDOWNNOTFILE(236),UDAERROR(237),SETIPERROR(238),SETIPIDERROR(239),CLEARSITE(240),QoSTEMOLATE(241),QoSTEMOLATENAME(242),
	UPDATAQoSERROR(243),QoSTEMOLATEDELETE(244),QoSTEMOLATEUPDATE(245),WORKIPERROR(246),WORKIPFORMAT1ERROR(247),WORKIPFORMAT2ERROR(248),WORKIPFORMAT3ERROR(249),
	WORKIPFORMAT4ERROR(250),WORKIPFORMAT5ERROR(251),WORKIPFORMAT6ERROR(252),NETIMEERROR(253),UPDATASITE(254),SITESYNCHRO(255),SITEDATA(256),COUNTLABLE(257),
	ONLINEUSER(258),SITEMANAGE(259),SITECLEAR(259),SITECLEARERROR(260),SITELCOK(261),SITELCOKERROR(262), FORCECLEARLOCK(263), FORCECLEARLOCKERROR(264),ROLEMANAGE(265),
	ROLEMANAGEERORR(266),ROLEMANAGEDELETE1(267),ROLEMANAGEDELETE2(268),ROLEMANAGEDELETE3(269),CRATEASITE1(270),CRATEASITE2(271),CRATEASITE3(272),CRATEASITE4(273),
	CRATEASITE5(274),CRATEASITE6(275),CRATEASITE7(276),SEGMENTDELETE1(277),SEGMENTDELETE2(278),SEGMENTDELETE3(279),ADDSEGMENT1(280),ADDSEGMENT2(281),ADDSEGMENT3(282),
	ADDSEGMENT4(283),ADDSEGMENT5(284),ADDSEGMENT6(285),ADDSEGMENT7(286),ADDSEGMENT8(287),ADDSEGMENT9(288),ADDSEGMENT10(289),UPDATECES(299),CRATECES(300),CES1(301),
	CES2(302),CES3(303),CES4(304),CES5(305),DUAL(306),ELANERROR1(307),ELANERROR2(308),ELANERROR3(309),ELANERROR4(310),ELANERROR5(311),
	DUAL1(312),DUAL2(313),DUAL3(314),DUAL4(315),ELINE(316),LOOPROTATE(317),PW(318),PW1(319),pw2(320),QIQ(321),PWOAM(322),
	SEGMENTOAM(323),TUNNELOAM(324),TESTSEGMENTOAM(325),TESTTUNNELOAM(326),TESTPWOAM(327),TNPERROR(328),TUNNELERROR1(329),
	TUNNELERROR2(330),TUNNELROATE(331),TUNNELROATE1(332),SINGCES(333),SINGCES1(334),SINGCES2(335),SINGCES3(336),SINGCES4(337),SINGCES5(338),SINGCES6(339),
	SINGCES7(340),SINGELAN(341),SINGELAN1(342),SINGELAN2(343),SINGELAN3(344),SINGELAN4(345),SINGELAN5(346),SINGELAN6(347),SINGELAN7(348),SINGELIN(349),
	SINGELINE1(350),SINGELINE2(351),SINGELINE3(352),SINGELINE4(353),SINGELINE5(354),SINGELINE6(355),SINGELINE7(356),SINGPORT(357),SINGPORT1(358),SINGPORT2(359),
	SINGPORT3(360),SINGPORT4(361),SINGPORT5(362),SINGETREE(363),SINGETREE1(364),SINGETREE2(365),SINGETREE3(366),SINGETREE4(367),SINGETREE5(368),SINGETREE6(369),SINGETREE7(370),
	SINGPW(371),SINGPW1(372),SINGPW2(373),SINGPW3(374),SINGPW4(375),SINGPW5(376),SINGPW6(377),SINGPW7(378),SINGPW8(379),SINGPW9(380),SINGPW10(381),SINGPW11(382),SINGPW12(383),
	SINGTUNNEL(384),SINGTUNNEL1(385),SINGTUNNEL2(386),SINGTUNNEL3(387),SINGTUNNEL4(388),SINGTUNNEL5(389),SINGTUNNEL6(390),SINGTUNNELROATE(391)
	,SINGTUNNELROATE1(392),SINGTUNNELROATE2(393),SINGTUNNEL7(394),SINGTUNNEL8(395),SINGTUNNEL9(396),SINGTUNNEL10(397),SINGTUNNEL11(398),SINGLAG(399),
	SINGLAG1(400),SINGLAG2(401),SINGLAG3(402),SINGLAG4(403),SINGLAG5(404),SINGLAG6(405),SINGLAG7(406),SINGLAG8(407)
	,SINGLAG9(408),OAMERROR(409),OAMERROR1(410),OAMERROR2(411),OAMERROR3(412),OAMERROR4(413),OAMERROR5(414),OAMERROR6(415),OAMERROR7(416),
	OAMERROR8(417),OAMERROR9(418),OAMERROR10(419),OAMPW(420),SEGMENTOAM1(421),TUNNELOAM1(422),TUNNELTOPO(423),TUNNELTOPO1(424),TUNNELTOPO2(425),
	TUNNELTOPO3(426),TUNNELTOPO4(427),TUNNELTOPO5(428),TUNNELTOPO6(429),TUNNELTOPO7(430),TUNNELTOPO8(431),TUNNELTOPO9(432),TUNNELTOPO10(433),TUNNELTOPO11(434),
	TUNNELTOPO12(435),TUNNELTOPO13(436),TUNNELTOPO14(437),ROUTEACTION(438),TUNNELCREATE(439),TUNNELCREATE1(440),TUNNELCREATE2(441),TUNNELCREATE3(442),
	TUNNELCREATE4(443),TUNNELCREATE5(444),TUNNELCREATE6(445),TUNNELCREATE8(446),TUNNELCREATE9(447),TUNNELCREATE10(448),QIQERROR(449),PWTOPOERROR(450),
    PWTOPOERROR1(451),PWTOPOERROR2(452),PWTOPOERROR3(453),PWTOPOERROR4(454),LOOPPRPTECT(455),LOOPPRPTECT1(456),ETREETOPO(457),ETREETOPO1(458),ETREETOPO2(459),ETREETOPO3(460),
    ETREETOPO4(461),ETREETOPO5(462),ELINEYOPO(463),ELINEYOPO1(464),ELINEYOPO2(465),ELINEYOPO3(466),ELINEYOPO4(467),ELINETOPO5(468),
	QOSERROR(469),FILEERROR(470),FILEHEARERROR(471),SELECTERROR(472),OFFLINEERROR(473),
	/****************************************************/
    ALARMSYSCHRO(474),ALARMREVERSAL(475),ALARMSHIELD(476),BUSINESSCESACTIVE(477),BUSINESSCESDOACTIVE(478),LOOPPROROTATE(479),QIQINSERT(480),QINUDAPTE(481),ELANACTIVE(482),ELANDOACTIVE(483)
    ,ELINEACIVE(484),ELINEDOACIVE(485),ETREEACIVE(486),ETREENOACTIVE(487),QIQDELETE(488),INSERTTNP(489),UPDATETNP(490)
    ,ACLDELETE(491),ALLCONFIGINSERT(492),BLACKANDWHITEMAC(493),BLACKANDWHITEMACINSERT(494),BLACKANDWHITEUPDATE(495)
    ,CESSINGACTIVE(496),CESSINGNOACTIVE(497),DISCARDSINGDELETE(498),DISCARDSINGINSERT(499),DUALPROTECTROTATE(500),
    DUALPROTECTROTATEINSERT(501),ELANSINGACTIVE(502),ELANSINGNOACTIVE(503),ELINESINGACTIVE(504),ELINESINGNOACTIVE(505)
    ,PORTQOSUPDATE(506),ETHLOOPSERVICEINSERT(507),ETHLOOPSERVICEUPDATE(508),ETHSERVICEDELETE(509),ETHSERVICESYS(510)
    ,ETREESINGACTIVE(511),ETREESINGNOACTIVE(512),GROUPSPREADINSERT(513),GROUPSPREADUPDATE(514),GROUPSPREADDELETE(515)
    ,L2CPINSERT(516),L2CPUPDATE(517),MACLEARNINGLIMITDELETE(518),MACMANAGMENT(519),MACMANAGMENTCREATE(520),MACMANAGMENTUPDATE(521)
    ,MACLEARNINGLIMITINSERT(522),MACLEARNINGLIMIUPDATE(523),E1PORTUPDATE(524),E1PORTSYS(525),V35PORTSYS(526)
    ,V35PORTUPDATE(527),V35PORTINSERT(528),PMCONFIGUPDATE(529),PMCONFIGSYC(530),PRIMANAGER(531),PRIMANAGERUPDATE(532)
    ,RESETMANAGER(533),SMARTFANCONFIG(534),SMARTFANCONFIGUPDATE(535),E1STATUSFIND(536),ETHLINKOAMFIND(537),ETHLINKOAMMEP(538)
    ,ETHLINKOAMSTATUS(539),ETHOAMPING(540),ETHOAMTRACE(541),PORTSTATUS(542),PWSTATUS(543),TUNNELSTATUS(544)
    ,SERVICESTATES(545),WRAPPINGSTATUS(546),STATICUNIDELETE(547),TDMLOOPCONFIG(548),TUNNELPROTECTRORATEINSERT(549)
    ,TUNNELPROTECTRORATEUPDATE(550),PWOAMSINGINSERT(551),PWOAMSINGUPDATE(552),SEGMENTOAMSINGINSERT(553),SEGMENTOAMSINGUPDATE(554)
    ,TUNNELOAMSINGINSERT(555),TUNNELOAMSINGUPDATE(556),INSERTROLEVLAUE(557),MOINTORSERVICEDB(558),MOINTORDB(559),EQUIMENT(560),SEGMENTSYSOAM(561)
    ,ETHSYSOAM(562),ETHLINKSYSOAM(563),ETHLINKDELETEOAM(564),ETHDELOAM(565),SEGMENTDELOAM(566),PWDELOAM(567),TUNNELDELOAM(568),SEGMENTOAMTESTSINGINSERT(569)
    ,SEGMENTOAMTESTSUP(570),SEGMENTOAMTESTSDEL(571),PWOAMTESTSINGINSERT(572),PWOAMTESTSUP(573),PWOAMTESTSDEL(574)
    ,TUNNELOAMTESTSINGINSERT(575),TUNNELOAMTESTSUP(576),TUNNELOAMTESTSDEL(577),ETREEPWERROR(578),ETREEACERROR(579),CREATEQIQNAME(580),CREATELABEL(581),BATCHUPDATE(582)
    ,REBOOTTIME(583),CANCLEREBOOTTIME(584)
    ,CREATESERVICE(585),UPDATESERVICE(586),DELETESERVICE(587),SYSSERVICE(588),CREATEACL(589),UPDATEACL(590),SYSACL(591),NNIPORTMOVED(592),UNIPORTMOVED(593),SYNCALLCONFIG(594)
    ,SYNCSTATICUNI(595),DELSTATICUNI(596),UPDATESTATICUNI(597),SYNCEXP(599),SYNCPHB(600),UPDATEPHB(601)
    ,NEWSTATICUNI(598),UPDATETIMESYN(602),INSERTTIMESYN(603),
	SEGMENTDELETE4(604),TUNNELERROR3(605),PWERROR1(606),ELINEERROR1(607),ETREEERROR1(608),ELANERROR6(609),DUALERROR(610),CESERROR1(611),
    QINQERROR1(612),LOOPERROR1(613),DCDUALERROR(614),DELETESERVICE1(615),DELETESECONDMAC(616),DELETEACL(617),DELETESTATICUNI(618),
    DELETEDCELAN(619),DELETEDCETREE(620),DELETEDCELINE(621),DELETEDCCES(622),DELETEDCPW(623),DELETEDCTUNNEL(624),DELETEDCLAG(625),DELETEDCPTPPORT(626),
    DELETEDCDQL(627),DELETEETHOAM(628),DELETEETHLINKOAM(629),DELETESEGMENTOAM(630),DELETEPWOAM(631),DELETETUNNELOAM(632),
    DELETEAXSEGMENTOAM(633),DELETEAXTUNNELOAM(634),DELETEAXPWOAM(635),AXTUNNELOAM(636),AXPWOAM(637),AXSEGMENTOAM(638),TNPDELETE(639),DUALACTIVE(640),DUALUNACTIVE(641),
    SYSSECONDMAC(642),SYSLOOPPROTECT(643),SYSTIMESYN(644),DELETEBLACKANDWHITEMAC(645),DELETEMACMANAGE(646),SYNCL2CP(647),SYSMACMANAGE(648),
    SYSBLACKWHITEMAC(649),OPENALARMSOUND(650),CLOSEALARMSOUND(651),SELFMANAGE(652),HANDLOCK(653),UPDATESETNAMERULE(654),SITEWORKIP(655),CANCLEBATCHUPGRADE(656),SYNCPM(657),
    ETHLINKOAMUPDATE(658),ETHLINKOAMINSERT(659),LAGSTATUS(660),PWPROTECTSELECT(661),LSPPROTECT(662),CLOCKSTATUS(663),CLOCKBASIC(664),DOWNSSERVISON(665),BATCHDOWNSSERVISON(666),
    CANCLEUPGRADE(667),SYNCSLOCK(668),ROATECLOCK(669),CLOCKSET(670),EMSPORT(671),EMSTUNNEL(672),EMSPW(673),EMSELINE(674),EMSETREE(675),EMSELAN(676),EMSCES(677),PORTEMS(678),
    TUNNELEMS(679),PWEMS(680),ELINEEMS(681),ETREEEMS(682),ELANEMS(683),CESEMS(684),NEUPLOAD(685),NEDOWNLOAD(686),TESTOAMSEGEMENTUPDATE(687),TESTOAMTUNNELUPDATE(688),TESTOAMPWUPDATE(689),

    TESTOAMSEGMENTDELETE(690),TESTOAMTUNNELDELETE(691),TESTOAMPWDELETE(692),TUNNELQOS(693),PWQOS(694),TNPDELETE1(695),TUNNELACTIVE(696),TUNNELUNACTIVE(697),PWACTIVE(698),PWUNACTIVE(699)
, ADDLSPNODE(700),DELETELSPNODE(701),DELETECCC(702),SYNCCCC(703),ACTIVECCC(704),UNACTIVECCC(705),SELECTNE(706),DELETECCCC1(707),CCCEMS(708),EMSCCC(709),SINGCCC1(710),SINGCCC2(711),INSERTCCC(712),UPDATECCC(713),TELNETMANAGE(714),
PTPPORTSTATUS(715),PTPBASICSTATUS(716);


    
		private int value;
	
	private EOperationLogType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public static EOperationLogType forms(int value) {
		for(EOperationLogType eOperationLogType : EOperationLogType.values()){
			if(value ==eOperationLogType.value)
				return eOperationLogType;
		}
		return null;
	}
	/**
	 * 返回    value
	 * @param cos
	 * @return
	 */
	public static int from(String object){
		if(object.equals(EOperationLogType.SEGEMENTINSERT.toString())){
			return 1;
		}else if(object.equals(EOperationLogType.SEGEMENTUPDATE.toString())){
			return 2;
		}else if(object.equals(EOperationLogType.SEGEMENTDELETE.toString())){
			return 3;
		}else if(object.equals(EOperationLogType.SEGEMENTSELECT.toString())){
			return 4;
		}else if(object.equals(EOperationLogType.TUNNELINSERT.toString())){
			return 5;
		}else if(object.equals(EOperationLogType.TUNNELUPDATE.toString())){
			return 6;
		}else if(object.equals(EOperationLogType.TUNNELDELETE.toString())){
			return 7;
		}else if(object.equals(EOperationLogType.TUNNELSELECT.toString())){
			return 8;
		}else if(object.equals(EOperationLogType.LOGINSUCCESS.toString())){
			return 9;
		}else if(object.equals(EOperationLogType.EXITSYSTEM.toString())){
			return 10;
		}else if(object.equals(EOperationLogType.TUNNELROTATE.toString())){
			return 11;
		}else if(object.equals(EOperationLogType.PWINSERT.toString())){
			return 12;
		}else if(object.equals(EOperationLogType.PWUPDATE.toString())){
			return 13;
		}else if(object.equals(EOperationLogType.PWDELETE.toString())){
			return 14;
		}else if(object.equals(EOperationLogType.PWSELECT.toString())){
			return 15;
		}else if(object.equals(EOperationLogType.PWVLAN.toString())){
			return 16;
		}else if(object.equals(EOperationLogType.ELINEINSERT.toString())){
			return 17;
		}else if(object.equals(EOperationLogType.ELINEUPDATE.toString())){
			return 18;
		}else if(object.equals(EOperationLogType.ELINEDELETE.toString())){
			return 19;
		}else if(object.equals(EOperationLogType.ELINESELECT.toString())){
			return 20;
		}else if(object.equals(EOperationLogType.ADDRAPID.toString())){
			return 21;
		}else if(object.equals(EOperationLogType.ETREEINSERT.toString())){
			return 22;
		}else if(object.equals(EOperationLogType.ETREEUPDATE.toString())){
			return 23;
		}else if(object.equals(EOperationLogType.ETREEDELETE.toString())){
			return 24;
		}else if(object.equals(EOperationLogType.ETREESELECT.toString())){
			return 25;
		}else if(object.equals(EOperationLogType.ELANINSERT.toString())){
			return 26;
		}else if(object.equals(EOperationLogType.ELANUPDATE.toString())){
			return 27;
		}else if(object.equals(EOperationLogType.ELANDELETE.toString())){
			return 28;
		}else if(object.equals(EOperationLogType.ELANSELECT.toString())){
			return 29;
		}else if(object.equals(EOperationLogType.CESINSERT.toString())){
			return 30;
		}else if(object.equals(EOperationLogType.CESUPDATE.toString())){
			return 31;
		}else if(object.equals(EOperationLogType.CESDELETE.toString())){
			return 32;
		}else if(object.equals(EOperationLogType.CESSELECT.toString())){
			return 33;
		}else if(object.equals(EOperationLogType.LOOPPROTECTINSERT.toString())){
			return 34;
		}else if(object.equals(EOperationLogType.LOOPPROTECTUPDATE.toString())){
			return 35;
		}else if(object.equals(EOperationLogType.LOOPPROTECTDELETE.toString())){
			return 36;
		}else if(object.equals(EOperationLogType.LOOPPROTECTSELECT.toString())){
			return 37;
		}else if(object.equals(EOperationLogType.SITESELECT.toString())){
			return 38;
		}else if(object.equals(EOperationLogType.SITEUPDATE.toString())){
			return 39;
		}else if(object.equals(EOperationLogType.ETHUPDATE.toString())){
			return 40;
		}else if(object.equals(EOperationLogType.ETHSYNCHRO.toString())){
			return 41;
		}else if(object.equals(EOperationLogType.PDHUPDATE.toString())){
			return 42;
		}else if(object.equals(EOperationLogType.PDHSYNCHRO.toString())){
			return 43;
		}else if(object.equals(EOperationLogType.SDHUPDATE.toString())){
			return 44;
		}else if(object.equals(EOperationLogType.SDHSYNCHRO.toString())){
			return 45;
		}else if(object.equals(EOperationLogType.SDHTIMESSLOTUPDATE.toString())){
			return 46;
		}else if(object.equals(EOperationLogType.SDHTIMESSLOTSYNCHO.toString())){
			return 47;
		}else if(object.equals(EOperationLogType.ACINSERT.toString())){
			return 48;
		}else if(object.equals(EOperationLogType.ACUPDATE.toString())){
			return 49;
		}else if(object.equals(EOperationLogType.ACDELETE.toString())){
			return 50;
		}else if(object.equals(EOperationLogType.ACSELECT.toString())){
			return 51;
		}else if(object.equals(EOperationLogType.UNLOADUPDATE.toString())){
			return 52;
		}else if(object.equals(EOperationLogType.UNLOADEXPORT.toString())){
			return 53;
		}else if(object.equals(EOperationLogType.UNLOADINPORT.toString())){
			return 54;
		}else if(object.equals(EOperationLogType.TUNNELSYNCHRO.toString())){
			return 55;
		}else if(object.equals(EOperationLogType.PWSYNCHRO.toString())){
			return 56;
		}else if(object.equals(EOperationLogType.ELINESYNCHRO.toString())){
			return 57;
		}else if(object.equals(EOperationLogType.ETREESYNCHRO.toString())){
			return 58;
		}else if(object.equals(EOperationLogType.ELANSYNCHRO.toString())){
			return 59;
		}else if(object.equals(EOperationLogType.CESSYNCHRO.toString())){
			return 60;
		}else if(object.equals(EOperationLogType.MCNUPDATE.toString())){
			return 61;
		}else if(object.equals(EOperationLogType.MCNSYNCHRO.toString())){
			return 62;
		}else if(object.equals(EOperationLogType.OSPFINSERT.toString())){
			return 63;
		}else if(object.equals(EOperationLogType.OSPFUPDATE.toString())){
			return 64;
		}else if(object.equals(EOperationLogType.OSPFDELETE.toString())){
			return 65;
		}else if(object.equals(EOperationLogType.OSPFSYNCHRO.toString())){
			return 66;
		}else if(object.equals(EOperationLogType.FREQUENCYINFOUPDATE.toString())){
			return 67;
		}else if(object.equals(EOperationLogType.FREQUENCYINSERT.toString())){
			return 68;
		}else if(object.equals(EOperationLogType.FREQUENCYUPDATE.toString())){
			return 69;
		}else if(object.equals(EOperationLogType.FREQUENCYDELETE.toString())){
			return 70;
		}else if(object.equals(EOperationLogType.FREQUENCYSYNCHRO.toString())){
			return 71;
		}else if(object.equals(EOperationLogType.FREQUENCYROATE.toString())){
			return 72;
		}else if(object.equals(EOperationLogType.TIMEMANAGEUPDATE.toString())){
			return 73;
		}else if(object.equals(EOperationLogType.TIMEMANAGESYNCHRO.toString())){
			return 74;
		}else if(object.equals(EOperationLogType.PORTINSERT.toString())){
			return 75;
		}else if(object.equals(EOperationLogType.PORTUPDATE.toString())){
			return 76;
		}else if(object.equals(EOperationLogType.PORTDELETE.toString())){
			return 77;
		}else if(object.equals(EOperationLogType.PORTSYNCHRO.toString())){
			return 78;
		}else if(object.equals(EOperationLogType.PORTMAP.toString())){
			return 79;
		}else if(object.equals(EOperationLogType.LOOPPTECTSYNCHRO.toString())){
			return 80;
		}else if(object.equals(EOperationLogType.QOSMODEINSERT.toString())){
			return 81;
		}else if(object.equals(EOperationLogType.QOSMODEUPDATE.toString())){
			return 82;
		}else if(object.equals(EOperationLogType.QOSMODEDELETE.toString())){
			return 83;
		}else if(object.equals(EOperationLogType.USERINSERT.toString())){
			return 84;
		}else if(object.equals(EOperationLogType.USERUPDATE.toString())){
			return 85;
		}else if(object.equals(EOperationLogType.USERDELETE.toString())){
			return 86;
		}else if(object.equals(EOperationLogType.USERISLOCK.toString())){
			return 87;
		}else if(object.equals(EOperationLogType.USERLOCK.toString())){
			return 88;
		}else if(object.equals(EOperationLogType.USERLOGOUT.toString())){
			return 89;
		}else if(object.equals(EOperationLogType.PERFORMANCEUPDATE.toString())){
			return 90;
		}else if(object.equals(EOperationLogType.ALARMDESCUPDATE.toString())){
			return 91;
		}else if(object.equals(EOperationLogType.SITEPORTSELECT.toString())){
			return 92;
		}else if(object.equals(EOperationLogType.CLENTINSERT.toString())){
			return 93;
		}else if(object.equals(EOperationLogType.CLIENTUPDATE.toString())){
			return 94;
		}else if(object.equals(EOperationLogType.CLENTDELETE.toString())){
			return 95;
		}else if(object.equals(EOperationLogType.SUBNETINSERT.toString())){
			return 96;
		}else if(object.equals(EOperationLogType.SUBNETUPDATE.toString())){
			return 97;
		}else if(object.equals(EOperationLogType.SUBNETDELETE.toString())){
			return 98;
		}else if(object.equals(EOperationLogType.PMLIMITCONFIGUPDATE.toString())){
			return 99;
		}else if(object.equals(EOperationLogType.SITELISTUPDATE.toString())){
			return 100;
		}else if(object.equals(EOperationLogType.SITELISTDELETE.toString())){
			return 101;
		}else if(object.equals(EOperationLogType.SITELISTSYNCHRO.toString())){
			return 102;
		}else if(object.equals(EOperationLogType.SITELISTISSUED.toString())){
			return 103;
		}else if(object.equals(EOperationLogType.SITELISTCURRECTTIME.toString())){
			return 104;
		}else if(object.equals(EOperationLogType.SITELISTINITIALIZTION.toString())){
			return 105;
		}else if(object.equals(EOperationLogType.SITELISTDOWNLODA.toString())){
			return 106;
		}else if(object.equals(EOperationLogType.PORTLAGINSERT.toString())){
			return 107;
		}else if(object.equals(EOperationLogType.PORTLAGUPDATE.toString())){
			return 108;
		}else if(object.equals(EOperationLogType.PORTLAGDELETE.toString())){
			return 109;
		}else if(object.equals(EOperationLogType.PORTLAGSYNCHRO.toString())){
			return 110;
		}else if(object.equals(EOperationLogType.CURRENTPERFORMANCESELECT.toString())){
			return 111;
		}else if(object.equals(EOperationLogType.HISPERFORMANCESELECT.toString())){
			return 112;
		}else if(object.equals(EOperationLogType.PERFORMANCETASKINSERT.toString())){
			return 113;
		}else if(object.equals(EOperationLogType.PERFORMANCETASKUPDATE.toString())){
			return 114;
		}else if(object.equals(EOperationLogType.PERFORMANCETASKDELETE.toString())){
			return 115;
		}else if(object.equals(EOperationLogType.PERFORMANCETASKFILTERSELECT.toString())){
			return 116;
		}else if(object.equals(EOperationLogType.CURRENTALARMEXPORT.toString())){
			return 117;
		}else if(object.equals(EOperationLogType.CURRENTALARMFILTERSELECT.toString())){
			return 118;
		}else if(object.equals(EOperationLogType.HISALARMEXPORT.toString())){
			return 119;
		}else if(object.equals(EOperationLogType.HISALARMFILTERSELECT.toString())){
			return 120;
		}else if(object.equals(EOperationLogType.SITESTATISTICSEXPORT.toString())){
			return 121;
		}else if(object.equals(EOperationLogType.SITEPHYCISEEXPORT.toString())){
			return 122;
		}else if(object.equals(EOperationLogType.SLOTPORT.toString())){
			return 123;
		}else if(object.equals(EOperationLogType.CARDEXPORT.toString())){
			return 124;
		}else if(object.equals(EOperationLogType.ALARMEXPORT.toString())){
			return 125;
		}else if(object.equals(EOperationLogType.LABELEXPORT.toString())){
			return 126;
		}else if(object.equals(EOperationLogType.PORTEXPORT.toString())){
			return 127;
		}else if(object.equals(EOperationLogType.SITEPHYPATHEXPORT.toString())){
			return 128;
		}else if(object.equals(EOperationLogType.INPORTHISPERFORMANCE.toString())){
			return 129;
		}else if(object.equals(EOperationLogType.EXPORTHISPERFORMANCE.toString())){
			return 130;
		}else if(object.equals(EOperationLogType.FIELDINSERT.toString())){
			return 131;
		}else if(object.equals(EOperationLogType.SITEINSERT.toString())){
			return 132;
		}else if(object.equals(EOperationLogType.SITECOPY.toString())){
			return 133;
		}else if(object.equals(EOperationLogType.FIELDDELETE.toString())){
			return 134;
		}else if(object.equals(EOperationLogType.LINECLSYNCHRO.toString())){
			return 135;
		}else if(object.equals(EOperationLogType.LINECLUPDATE.toString())){
			return 136;
		}else if(object.equals(EOperationLogType.EXTERNALINSERT.toString())){
			return 137;
		}else if(object.equals(EOperationLogType.EXTERNALUPDATE.toString())){
			return 138;
		}else if(object.equals(EOperationLogType.EXTERNALSYNCHRO.toString())){
			return 139;
		}else if(object.equals(EOperationLogType.MSPPROTECTINSERT.toString())){
			return 153;
		}else if(object.equals(EOperationLogType.MSPPROTECTUPDATE.toString())){
			return 154;
		}else if(object.equals(EOperationLogType.MSPPROTECTDELETE.toString())){
			return 155;
		}else if(object.equals(EOperationLogType.MSPPROTECTSYNCHRO.toString())){
			return 156;
		}else if(object.equals(EOperationLogType.DUALPROTECTINSERT.toString())){
			return 157;
		}else if(object.equals(EOperationLogType.DUALPROTECTUPDATE.toString())){
			return 158;
		}else if(object.equals(EOperationLogType.DUALPROTECTDELETE.toString())){
			return 159;
		}else if(object.equals(EOperationLogType.DUALPROTECTSYNCHRO.toString())){
			return 160;
		}
		else if(object.equals(EOperationLogType.DELETEROLE.toString())){
			return 161;
		}else if(object.equals(EOperationLogType.INSERTROLE.toString())){
			return 162;
		}else if(object.equals(EOperationLogType.UPDATEROLE.toString())){
			return 163;
		}else if(object.equals(EOperationLogType.EXPORTPERFORMANCE.toString())){
			return 163;
		}else if(object.equals(EOperationLogType.EXPORTPERFORMANCE.toString())){
			return 164;
		}else if(object.equals(EOperationLogType.EXPMAPPINGINSERT.toString())){
			return 165;
		}else if(object.equals(EOperationLogType.EXPMAPPINGSYNCHRO.toString())){
			return 166;
		}else if(object.equals(EOperationLogType.VLANPRITOCOLORMAPPINGINSERT.toString())){
			return 167;
		}else if(object.equals(EOperationLogType.VLANPRITOCOLORMAPPINGSYNCHRO.toString())){
			return 168;
		}else if(object.equals(EOperationLogType.COSTOVLANPRIMAPPINGINSERT.toString())){
			return 169;
		}else if(object.equals(EOperationLogType.COSTOVLANPRIMAPPINGSYNCHRO.toString())){
			return 170;
		}else if(object.equals(EOperationLogType.CARDINSERT.toString())){
			return 171;
		}else if(object.equals(EOperationLogType.CARDDELETE.toString())){
			return 172;
		}else if(object.equals(EOperationLogType.REMOVELOGINLOG.toString())){
			return 173;
		}else if(object.equals(EOperationLogType.REMOVEOPERATION.toString())){
			return 174;
		}else if(object.equals(EOperationLogType.EXPORTOPERATELOG.toString())){
			return 175;
		}else if(object.equals(EOperationLogType.EXPORTLOGINLOG.toString())){
			return 176;
		}else if(object.equals(EOperationLogType.EXPMAPPINGDELETE.toString())){
			return 177;
		}else if(object.equals(EOperationLogType.VLANPRITOCOLORMAPPINGDELETE.toString())){
			return 178;
		}else if(object.equals(EOperationLogType.COSTOVLANPRIMAPPINGDELETE.toString())){
			return 179;
		}else if(object.equals(EOperationLogType.UPDATEEXTERNALCLOCK.toString())){
			return 180;
		}else if(object.equals(EOperationLogType.PROFESSEXPORT.toString())){
			return 181;
		}else if(object.equals(EOperationLogType.CREATEGROUP.toString())){
			return 182;
		}else if(object.equals(EOperationLogType.UPDATEGROUPID.toString())){
			return 183;
		}else if(object.equals(EOperationLogType.DELETEGROUPID.toString())){
			return 184;
		}else if(object.equals(EOperationLogType.DELETELOGINMANAGER.toString())){
			return 185;
		}else if(object.equals(EOperationLogType.INSERTLOGINMANAGER.toString())){
			return 186;
		}else if(object.equals(EOperationLogType.UPDATELOGINMANAGER.toString())){
			return 187;
		}else if(object.equals(EOperationLogType.DELETENAMERULE.toString())){
			return 188;
		}else if(object.equals(EOperationLogType.INSERTNAMERULE.toString())){
			return 189;
		}else if(object.equals(EOperationLogType.UPDATENAMERULE.toString())){
			return 190;
		}else if(object.equals(EOperationLogType.DUALROTATE.toString())){
			return 196;
		}else if(object.equals(EOperationLogType.SECONDMACDELETE.toString())){
			return 197;
		}else if(object.equals(EOperationLogType.SECONDMACINSERT.toString())){
			return 199;
		}else if(object.equals(EOperationLogType.SECONDMACUPDATE.toString())){
			return 200;
		}
		return 0;
	}
	@Override
	public String toString(){
		switch (value){
		case 1:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SEGEMENT_INSERT);//新建段（1
		case 2:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_SEGMENT);//修改段（2）
		case 3:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SEGEMENT_DELECT);//删除段（3）
		case 4:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SEGEMENT_SELECT);//查询段（4）	
		case 5:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_TUNNEL);//新建 Tunnel
		case 6:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_TUUNEL_UPDATE);//查询 Tunnel
		case 7:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_TUUNEL_DELETE);//查询 Tunnel
		case 8:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_TUUNEL_SELECT);//查询 Tunnel
		case 9:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_LOGIN_SUCCESS);//登陆
		case 10:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_EXIT_SYSTEM);//退出
		case 11:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_TUNNEL_ROTATE);//倒换
		case 12:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_PW);//新建PW
		case 13:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PW_UPDATE);//新建PW
		case 14:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PW_DELETE);//新建PW
		case 15:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PW_SELECT);//搜索PW
		case 16:
			return ResourceUtil.srcStr(StringKeysPanel.PANEL_PW_PORT_CONFIGE);//搜索PW
		case 17:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ELINE_INSERT);//新建Eline
		case 18:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ELINE_UPDATE);//新建Eline
		case 19:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ELINE_DELETE);//新建Eline
		case 20:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ELINE_SELECT);//新建Eline
		case 21:
			return ResourceUtil.srcStr(StringKeysBtn.BTN_ADD_RAPID);//快速新建
		case 22:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_ETREE);//新建 etree
		case 23 :
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ETREE_UPDATE);//新建 etree
		case 24:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ETREE_DELETE);//新建 etree
		case 25:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ETREE_SELECT);//新建 etree
		case 26:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_ELAN);//新建 elan
		case 27:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ELAN_UPDATE);//新建 elan
		case 28:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ELAN_DELETE);//新建 elan
		case 29:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ELAN_SELECT);//新建 elan
		case 30:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_CES);//新建 ces
		case 31:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CES_UPDATE);//新建 elan
		case 32:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CES_DELETE);//新建 elan
		case 33:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CES_SELECT);//新建 elan
		case 34:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_LOOPPROTECT_INSERT);//新建loopprotect
		case 35:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_LOOPPROTECT_UPDATE);//新建loopprotect
		case 36:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_LOOPPROTECT_DELETE);//新建loopprotect
		case 37:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_LOOPPROTECT_SELECT);//新建loopprotect
		case 38:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SITE_SELECT);//查询 SITE
		case 39:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SITE_UPDATE);//更新 SITE
		case 40:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ETH_UPDATE);//修改 ETH
		case 41:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ETH_SYNCHRO);//修改 ETH
		case 42:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PDH_UPDATE);//修改 PDH
		case 43:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PDH_SYNCHRO);//修改 PDH
		case 44:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SDH_UPDATE);//修改 SDH
		case 45:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SDH_SYNCHRO);//修改 SDH
		case 46:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SDHTIMESLOG_UPDATE);//修改 SDH时隙
		case 47:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SDHTIMESLOG_SYNCHRO);//修改 SDH时隙
		case 48:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_AC);//新建AC
		case 49:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_AC_UPDATE);//新建AC
		case 50:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_AC_DELETE);//新建AC
		case 51:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_AC_SELECT);//新建AC
		case 52:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_UNLOAD_UPDATE);//修改转储文件路径
		case 53:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_UNLOAD_EXPORT);//导出
		case 54:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_UNLOAD_INPORT);//数据恢复
		case 55:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_TUNNEL_SYNCHRO);//同步  TUNNEL
		case 56:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PW_SYNCHRO);//同步  TUNNEL
		case 57:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ELINE_SYNCHRO);//同步  ELINE
		case 58:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ETREE_SYNCHRO);//同步  ELINE
		case 59:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ELAN_SYNCHRO);//同步  ELINE
		case 60:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CES_SYNCHRO);//同步  ELINE
		case 61:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_MCN_UPDATE);//修改MCN
		case 62:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_MCN_SYNCHRO);//新建MCN
		case 63:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_OSPF);//新建OSPF
		case 64:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_OSPF_UPDATE);//新建MCN
		case 65:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_OSPF_DELETE);//新建MCN
		case 66:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_OSPF_SYNCHRO);//新建MCN
		case 67:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_FREQUENCYINFO_UPDATE);//更新网元时钟属性
		case 68:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_FREQUENCY_INSERT);//新建时钟源
		case 69:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_FREQUENCY_UPDATE);//新建时钟源
		case 70:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_FREQUENCY_DELETE);//新建时钟源
		case 71:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_FREQUENCY_SYNCHRO);//新建时钟源
		case 72:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_FREQUENCY_ROATE);//新建时钟源
		case 73:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_TIMEMANAGE_UPDATE);//更新  网元PTP配置
		case 74:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_TIMEMANAGE_SYNCHRO);//更新  网元PTP配置
		case 75:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PORT_INSERT);//新建 端口配置
		case 76:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PORT_UPDATE);//新建 端口配置
		case 77:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PORT_DELETE);//新建 端口配置
		case 78:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PORT_SYNCHRO);//新建 端口配置
		case 79:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PORT_MAP);//新建 端口配置
		case 80:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_LOOPPROTECT_SYNCHRO);//同步 环保护
		case 81:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_QOSMODE_INPORT);//新建QOS模板
		case 82:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_QOSMODE_UPDATE);//新建QOS模板
		case 83:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_QOSMODE_DELETE);//新建QOS模板
		case 84:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_USER);//新建用户
		case 85:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_USER_UPDATE);//新建用户
		case 86:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_USER_DELETE);//新建用户
		case 87:
			return ResourceUtil.srcStr(StringKeysObj.UNLOCK_USER);//新建用户
		case 88:
			return ResourceUtil.srcStr(StringKeysObj.LOCK_USER);//新建用户
		case 89:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_USER_LOGOUT);//新建用户
		case 90:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PERFORMANCE_UPDATE);//修改当前性能
		case 91:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ALARMDESC_UPDATE);//修改告警
		case 92:
			return ResourceUtil.srcStr(StringKeysTab.TAB_SITESEARCH);//修改告警
		case 93:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CLIENTMANAGER_INSERT);//新建客户信息
		case 94:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CLIENTMANAGER_UPDATE);//新建客户信息
		case 95:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CLIENTMANAGER_DELETE);//新建客户信息
		case 96:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_SUBNET);//新建子网
		case 97:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SUBNET_UPDATE);//新建子网
		case 98:
			return ResourceUtil.srcStr(StringKeysMenu.MENU_DELETESUBNET);//新建子网
		case 99:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PMLIMITCONFIG_UPDATE);//新建子网
		case 100:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SITELIST_UPDATE);//修改网元
		case 101:
			return ResourceUtil.srcStr(StringKeysMenu.MENU_DELETENE);//修改网元
		case 102:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SITELIST_SYNCHRO);//修改网元
		case 103:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SITELIST_ISSUED);//修改网元
		case 104:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SITELIST_CURRECTTIME);//修改网元
		case 105:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SITELIST_INITIALIZTION);//修改网元
		case 106:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SITELIST_DOWNLODA);//修改网元
		case 107:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_PORT_AGGREGATION);//新建LAG
		case 108:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PORTLAG_UPDATE);//新建LAG
		case 109:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PORTLAG_DELETE);//新建LAG
		case 110:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PORTLAG_SYNCHRO);//新建LAG
		case 111:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CURRENTPERFORMANCE_FILTERSELECT);//设置当前性能查询条件
		case 112:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_HISPERFORMANCE_FILTERSELECT);//设置当前性能查询条件
		case 113:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PERFORMANCETASK_INSERT);//新建长期性能任务
		case 114:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PERFORMANCETASK_UPDATE);//新建长期性能任务
		case 115:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PERFORMANCETASK_DELETE);//新建长期性能任务
		case 116:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PERFORMANCETASK_FILTERSELECT);//新建长期性能任务
		case 117:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CURRENTALARM_EXPORT);//导出当前告警
		case 118:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CURRENTALARM_FILTERSELECT);//导出当前告警
		case 119:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_HISALARM_EXPORT);//导出离线告警
		case 120:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_HISALARM_FILTERSELECT);//导出离线告警
		case 121:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SITESTATISTICS_EXPORT);//导出网元配置信息统计
		case 122:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SITEPHYCISE_EXPORT);//导出网元配置信息统计
		case 123:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SLOT_EXPORT);//导出网元配置信息统计
		case 124:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CARD_EXPORT);//导出网元配置信息统计
		case 125:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ALARM_EXPORT);//导出网元配置信息统计
		case 126:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_LABEL_EXPORT);//导出网元配置信息统计
		case 127:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PORT_EXPORT);//导出网元配置信息统计
		case 128:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SITEPHYPATH_EXPORT);//导出网元配置信息统计
		case 129:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_HISPERFORMANCE_INPORT);//导入历史性能
		case 130:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_HISPERFORMANCE_EXPORT);//导入历史性能
		case 131:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_FIELD);//新建域
		case 132:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_SITE);//新建域
		case 133:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SITE_COPY);//新建域
		case 134:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_FIELD_DELETE);//新建域
		case 135:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_LINECL_SYNCHRO);//同步线路时钟接口
		case 136:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_LINECL_UPDATE);//同步线路时钟接口
		case 137:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_EXTERNAL_INSERT);//新建外时钟接口
		case 138:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_EXTERNAL_UPDATE);//新建外时钟接口
		case 139:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_EXTERNAL_SYNCHRO);//新建外时钟接口
		case 140:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SEGMENTOAM_INSERT);//新建段OAM
		case 141:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_SEGMENTOAM_UPDATE);//新建段OAM
		case 142:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_TUNNELOAM_INSERT);//新建段OAM
		case 143:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_TUNNELOAM_UPDATE);//新建段OAM
		case 144:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PWOAM_INSERT);//新建段OAM
		case 145:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PWOAM_UPDATE);//新建段OAM
		case 146:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ELINEOAM_INSERT);//新建段OAM
		case 147:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ELINEOAM_UPDATE);//新建段OAM
		case 148:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ETH_UNIOAM_INSERT);//新建段OAM
		case 149:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ETH_UNIOAM_UPDATE);//新建段OAM
		case 150:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ETHERNETOAM_INSERT);//新建段OAM
		case 151:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ETHERNETOAM_UPDATE);//新建段OAM
		case 152:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ETHERNETOAM_DELETE);//新建段OAM
		case 153:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_MSPPROTECT_INSERT);//新建msp保护
		case 154:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_MSPPROTECT_UPDATE);//修改msp保护
		case 155:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_MSPPROTECT_DELETE);//删除msp保护
		case 156:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_MSPPROTECT_SYNCHRO);//同步msp保护
		case 157:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_DUAL_INSERT);//新建双规保护
		case 158:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_DUAL_UPDATE);//修改双规保护
		case 159:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_DUAL_DELETE);//删除双规保护
		case 160:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_DUAL_SYNCHRO);//同步双规保护
		case 161:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ROLE_DELETE);//删除角色
		case 162:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ROLE_INSERT);//新建角色
		case 163:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_ROLE_UPDATE);//修改角色
		case 164:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_PERFORMANCE_EXPORT);//导出性能统计
		case 165:
			return ResourceUtil.srcStr(StringKeysLbl.LBL_EXPMODEL_UPDATE);//exp映射新建
		case 166:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_EXPMAPPINGSYNCHRO);//exp映射同步
		case 167:
			return ResourceUtil.srcStr(StringKeysLbl.LBL_VLANPRITOCOLORMODEL_INSERT);//.VLANPRITOCOLOR映射新建
		case 168:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_VLANPRITOCOLORMAPPINGSYNCHRO);//.VLANPRITOCOLOR映射同步
		case 169:
			return ResourceUtil.srcStr(StringKeysLbl.LBL_PRIORITYTOVLANPRIMODEL_INSERT);//COSTOVLANPRI映射新建
		case 170:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_COSTOVLANPRIMAPPINGSYNCHRO);//COSTOVLANPRI映射同步
		case 171:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CARD_INSERT);//添加板卡
		case 172:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_CARD_DELETE);//添加板卡
		case 173:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_REMOVE_LOGINLOG);//移除 登陆 日志
		case 174:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_REMOVE_OPERATIONLOG);
		case 175:
			return ResourceUtil.srcStr(StringKeysOperaType.EXPORTOPERATELOG);//导出 操作日志
		case 176:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_EXPORT_LOGINLOG);//导出 操作日志
		case 177:
			return ResourceUtil.srcStr(StringKeysLbl.LBL_EXPMODEL_DELETE);//导出 操作日志
		case 178:
			return ResourceUtil.srcStr(StringKeysLbl.LBL_VLANPRITOCOLORMODEL_DELETE);//导出 操作日志
		case 179:
			return ResourceUtil.srcStr(StringKeysLbl.LBL_PRIORITYTOVLANPRIMODEL_DELETE);//导出 操作日志
		case 180:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_EXTERNALCLOCK);//修改外时钟接口
		case 181:
			return ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT)+ ResourceUtil.srcStr(StringKeysTitle.TIT_PROFESS_COUNT);//导出业务信息
		case 182:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_GROUP);//创建组	
		case 183:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_GROUP);//更新组
		case 184:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_DELETE_GROUP);//删除组
		case 185:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_DELETE_IP);//删除ip设置段
		case 186:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_INSERT_IP);//新建IP设置段
		case 187:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_IP);//更新ip设置段
		case 188:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_DETELE_NAMERULE);//删除拓扑命名规则
		case 189:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_INSERT_NAMERULE);//删除拓扑命名规则	
		case 190:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_NAMERULE);//更新拓扑命名规则	
		case 191:
			return ResourceUtil.srcStr(StringKeysMenu.MENU_PRIORITY_PRI);//端口pri映射
		case 192:
			return ResourceUtil.srcStr(StringKeysMenu.MENU_PORT_DISCOED);//端口丢弃流设置
		case 193:
			return ResourceUtil.srcStr(StringKeysMenu.MENU_PORT_DISCOED);//删除所有端口的所有VLAN
		case 194:
			return ResourceUtil.srcStr(StringKeysMenu.MENU_PORT_DISCOED);//端口丢弃流设置
		case 195:
			return ResourceUtil.srcStr(StringKeysBtn.BTN_CLEAR_ALARM);//清除告警
		case 196:
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_DUAL_ROTATE);//双规倒换

		case 197:
			return ResourceUtil.srcStr(StringKeysLbl.LBL_SECONDMAC_DELETE);//二层mac删除
		case 199:
			return ResourceUtil.srcStr(StringKeysLbl.LBL_SECONDMAC_INSERT);//二层mac删除
		case 200:
			return ResourceUtil.srcStr(StringKeysLbl.LBL_SECONDMAC_UPDATE);//二层mac删除						
		case 198:
			return ResourceUtil.srcStr(StringKeysMenu.MENU_PORT_2LAYER_ATTR);//修改端口2层属性
			//--------------------------------------------详细的操作错误记录
		case 201:
			return ResourceUtil.srcStr(StringKeysTip.TIP_PASSWORDERRORANAGE);//密码错误
		case 202:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_POWER);//权限不足
		case 203:
			return ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL);//请填写完整
		case 204:
			return ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION4);//请填写完整
		case 205:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_POWER);//权限不足
		case 206:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_POWER);//权限不足
		case 207:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_POWER);//权限不足
		case 208:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_POWER);//权限不足
		case 209:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_POWER);//权限不足
		case 210:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_POWER);//权限不足
		case 211:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_POWER);//权限不足
		case 212:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_POWER);//权限不足
		case 213:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_SYSTEMLOCK);//此账号系统已自动锁定，30分钟后自动解锁
		case 214:
			return ResourceUtil.srcStr(StringKeysTip.TIP_NOT_USER);//用户不存在
		case 215:
			return ResourceUtil.srcStr(StringKeysTip.TIT_IP_IN);//登陆IP是否在范围之内的提示
		case 216:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_ADMINLOCK);//管理员已经锁定此账号
		case 217:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_SYSTEMLOCK);//此账号系统已自动锁定，30分钟后自动解锁
		case 218:
			return ResourceUtil.srcStr(StringKeysTip.TIP_USER_PASSWORK_ERROR);//用户名或密码错误
		case 219:
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONNECTION_ERROR);//连接服务器异常
		case 220:
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONNECTION_ERROR);//超过最大连接数
		case 221:
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONNECTION_ERROR);//服务器版本过期
		case 222:
			return ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION5);
		case 223:
			return ResourceUtil.srcStr(StringKeysTip.TIP_PORTISUSEDBYSEGMENT);
		case 224:
			return ResourceUtil.srcStr(StringKeysTip.TIP_PORTISUSEDBYACPORT);
		case 225:
			return ResourceUtil.srcStr(StringKeysTip.TIP_EXIST_GROUP);
		case 226:
			return ResourceUtil.srcStr(StringKeysTip.TIP_AC_NEHASSEGMENT);
		case 227:
			return ResourceUtil.srcStr(StringKeysTip.TIP_NEHASSEGMENT);
		case 228:
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNEL_NEHASSEGMENT);
		case 229:
			return ResourceUtil.srcStr(StringKeysTip.TIP_SUBNET_DELETE_NODE);
		case 230:
			return ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION6);
		case 231:
			return ResourceUtil.srcStr(StringKeysTab.TAB_VLANPRITOCOLORTEMPLATE)+ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION7);
		case 232:
			return ResourceUtil.srcStr(StringKeysMenu.TAB_PRIORITYTOVLANPRITEMPLATE_T)+ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION7);
		case 233:
			return ResourceUtil.srcStr(StringKeysTab.TAB_COSTOEXPTEMPLATE)+ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION7);
		case 234:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION8)+ResourceUtil.srcStr(StringKeysTip.TIP_UP_DOWN);
		case 235:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION8)+ ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 236:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION8)+ ResourceUtil.srcStr(StringKeysTip.TiP_POSITION_FILEEXIT);
		case 237:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION9)+ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL);
		case 238:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION10)+ResourceUtil.srcStr(StringKeysTip.TIP_SITE_IP_EXIST);
		case 239:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION10)+ResourceUtil.srcStr(StringKeysTip.TIP_SITE_ID_EXIST);
		case 240:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION11)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 241:
			return ResourceUtil.srcStr(StringKeysTip.TIP_QOS_TEMPLATENAME_NOTEMPTY);
		case 242:
			return ResourceUtil.srcStr(StringKeysTip.TIP_QOS_TEMPLATENAME_ISEXSIT);
		case 243:
			return ResourceUtil.srcStr(StringKeysTip.TIP_QOSISNOTENOUGH);
		case 244:
			return ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE);
		case 245:
			return ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 246:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION12)+ResourceUtil.srcStr(StringKeysLbl.LBL_MUST_WORKIP);
		case 247:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION13)+ResourceUtil.srcStr(StringKeysTip.TIP_IP_ERROR);
		case 248:
			return ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION14)+ResourceUtil.srcStr(StringKeysTip.TIP_IP_ERROR);
		case 249:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION15)+ResourceUtil.srcStr(StringKeysTip.TIP_IP_ERROR);
		case 250:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION16)+ResourceUtil.srcStr(StringKeysTip.TIP_IP_ERROR);
		case 251:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION17)+ResourceUtil.srcStr(StringKeysTip.TIP_IP_ERROR);
		case 252:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION18)+ResourceUtil.srcStr(StringKeysTip.TIP_IP_ERROR);
		case 253:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION19)+ResourceUtil.srcStr(StringKeysTip.TIP_TIMEERROR);
		case 254:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION20)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 255:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION21)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 256:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION22)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 257:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION23)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 258:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION24)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE);
		case 259:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION25)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE);
		case 260:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION25)+ResourceUtil.srcStr(StringKeysTip.TIP_USER_POWER);
		case 261:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION25)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE);
		case 262:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION25)+ResourceUtil.srcStr(StringKeysTip.TIP_USER_POWER);
		case 263:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION27)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_LOCKED_SITE);
		case 264:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION26)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 265:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION27)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_ROLE);
		case 266:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION27)+ResourceUtil.srcStr(StringKeysTip.TIP_ROLE_EXIT);
		case 267:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION28)+ResourceUtil.srcStr(StringKeysTip.TIP_USERNAMEBESTOW);
		case 268:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION28)+ResourceUtil.srcStr(StringKeysTip.TIP_USER_DEFAULTUSER);
		case 269:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION28)+ResourceUtil.srcStr(StringKeysTip.TIP_USER_ROLE_USERING);
		case 270:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION29)+ResourceUtil.srcStr(StringKeysTip.TIP_GROUP_BELONG);
		case 271:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION29)+ResourceUtil.srcStr(StringKeysTip.TIP_FIELDEXISTMNE);
		case 272:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION29)+ResourceUtil.srcStr(StringKeysTip.TIP_SITENUM_BEYOND);
		case 273:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION29)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 274:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION29)+ResourceUtil.srcStr(StringKeysTip.TIP_SITE_IP_EXIST);
		case 275:
			return ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION29)+ResourceUtil.srcStr(StringKeysTip.TIP_SITE_ID_EXIST);
		case 276:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION29)+ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL);
		case 277:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION30)+ResourceUtil.srcStr(StringKeysTip.TIP_CLEAN_OAM);
		case 278:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION30)+ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_SEGMENT_TUNNEL);
		case 279:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION30)+ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_SEGMENT_QINQ);
		case 280:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION31)+ResourceUtil.srcStr(StringKeysTip.TIP_QOS_FILL);
		case 281:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION32)+ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE);
		case 282:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION32)+ResourceUtil.srcStr(StringKeysTip.TIP_QOS_ALARM);
		case 283:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION32)+ResourceUtil.srcStr(StringKeysTip.TIP_QOS_FILL);
		case 284:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION32)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 285:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION33)+ResourceUtil.srcStr(StringKeysTip.TIP_CREATE_SEGMENT_SPEED);
		case 286:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION33)+ResourceUtil.srcStr(StringKeysTip.TIP_PORTNULL);
		case 287:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION34)+ResourceUtil.srcStr(StringKeysTip.TIP_PORTNULL);
		case 288:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION33)+ResourceUtil.srcStr(StringKeysTip.TIP_PORTTYPEANDENABLE);
		case 289:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION33)+ResourceUtil.srcStr(StringKeysTip.TIP_PORT_OCCUPY);
		case 299:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION35)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 300:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION36)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 301:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION36)+ResourceUtil.srcStr(StringKeysTip.TIP_APORTTABLEISZERO);
		case 302:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION36)+ResourceUtil.srcStr(StringKeysTip.TIP_ZPORTTABLEISZERO);
		case 303:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION36)+ResourceUtil.srcStr(StringKeysTip.TIP_ZPORTTABLEISZERO);
		case 304:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION36)+ResourceUtil.srcStr(StringKeysTip.TIP_ATOZPROTNUMBERISNOTSAME);
		case 305:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION36)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECTPWISNOTENOUGH);
		case 306:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION37)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 307:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION38)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 308:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION38)+ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_SITE);
		case 309:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION38)+ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE);
		case 310:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION38)+ResourceUtil.srcStr(StringKeysTip.TIP_PWANDACNOMATE);
		case 311:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION38)+ResourceUtil.srcStr(StringKeysTip.TIP_PWANDACNOMATE);
		case 312:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION37)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_ROOT_PORT);
		case 313:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION37)+ResourceUtil.srcStr(StringKeysTip.TIP_STAND_PROTECT);
		case 314:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION37)+ResourceUtil.srcStr(StringKeysTip.TIP_STAND_MAIN);
		case 315:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION37)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PW_ERROR);
		case 316:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION39)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 317:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION40)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 318:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION41)+ResourceUtil.srcStr(StringKeysTip.TIP_CLEAN_OAM);
		case 319:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION42)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 320:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION43)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 321:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION44)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 322:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION45)+ResourceUtil.srcStr(StringKeysTip.TIP_NO_PW);
		case 323:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION46)+ResourceUtil.srcStr(StringKeysTip.LBL_NO_PORT);
		case 324:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION47)+ResourceUtil.srcStr(StringKeysTip.TIP_NO_JOB_PATH);
		case 325:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION48)+ResourceUtil.srcStr(StringKeysTip.TIP_OAM_LIMIT_10);
		case 326:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION49)+ResourceUtil.srcStr(StringKeysTip.TIP_OAM_LIMIT_10);
		case 327:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION50)+ResourceUtil.srcStr(StringKeysTip.TIP_OAM_LIMIT_10);
		case 328:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION51)+ResourceUtil.srcStr(StringKeysTip.TIP_NO_PROTECT_PATH);
		case 329:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION52)+ResourceUtil.srcStr(StringKeysTip.TIP_CLEAN_OAM);
		case 330:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION90)+ResourceUtil.srcStr(StringKeysTip.TIP_CLEAN_OAM);
		case 331:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION53)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 332:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION53)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_1TO1);
		case 333:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION54)+ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_NODE);
		case 334:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION54)+ ResourceUtil.srcStr(StringKeysTip.TIP_UPDATE_NODE);
		case 335:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION55)+ResourceUtil.srcStr(StringKeysTip.TIP_DATAISNULL);
		case 336:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION55)+ResultString.QUERY_FAILED;
		case 337:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION56)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 338:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION56)+ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE);
		case 339:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION56)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PW);
		case 340:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION56)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_TIMESLOT);
			
		case 341:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION57)+ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_NODE);
		case 342:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION58)+ResourceUtil.srcStr(StringKeysTip.TIP_DATAISNULL);
		case 343:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION58)+ResultString.QUERY_FAILED;
		case 344:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION59)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 345:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION59)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PW_ERROR);
		case 346:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION59)+ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE);
		case 347:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION59)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_AC);
		case 348:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION59)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PW);
			
		case 349:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION60)+ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_NODE);
		case 350:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION61)+ResourceUtil.srcStr(StringKeysTip.TIP_DATAISNULL);
		case 351:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION61)+ResultString.QUERY_FAILED;
		case 352:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION62)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 353:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION62)+ResourceUtil.srcStr(StringKeysTip.TIP_QOSISNOTENOUGH);
		case 354:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION62)+ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE);
		case 355:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION62)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_AC);
		case 356:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION62)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PW);
		case 357:
			return   ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION63)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 358:
			return   ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION63)+ResourceUtil.srcStr(StringKeysTip.TIP_USEDBYLAG);
		case 359:
			return   ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION64)+ResultString.QUERY_FAILED;
		case 360:
			return   ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION65)+ ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 361:
			return   ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION65)+ ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_TYPE);
		case 362:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION65)+ResourceUtil.srcStr(StringKeysTip.TIP_QOS_ALARM);
		case 363:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION89)+ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_NODE);
		case 364:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION66)+ResourceUtil.srcStr(StringKeysTip.TIP_DATAISNULL);
		case 365:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION66)+ResultString.QUERY_FAILED;
		case 366:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION67)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 367:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION67)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PW_ERROR);
		case 368:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION67)+ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE);
		case 369:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION67)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_AC);
		case 370:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION67)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PW);
		case 371:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION68)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 372:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION68)+ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_NODE);
		case 373:
			return ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION68)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 374:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION68)+ResourceUtil.srcStr(StringKeysTip.TIP_UPDATE_NODE);
		case 375:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION69)+ResultString.QUERY_FAILED;
		case 376:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION70)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 377:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION70)+ResourceUtil.srcStr(StringKeysTip.TIP_LABEL_OCCUPY);
		case 378:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION70)+ResourceUtil.srcStr(StringKeysTip.TIP_TUNNEL_LCK);
		case 379:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION70)+ResourceUtil.srcStr(StringKeysTip.TIP_QOSISNOTENOUGH_E1);
		case 380:
			return   ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION70)+ResourceUtil.srcStr(StringKeysTip.TIP_QOSISNOTENOUGH);
		case 381:
			return   ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION71)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 382:
			return   ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION71)+ResourceUtil.srcStr(StringKeysTip.TIP_UPDATE_NODE);
		case 383:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION71)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 384:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION72)+ResourceUtil.srcStr(StringKeysTip.TIP_CLEAN_OAM);
		case 385:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION72)+ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_NODE);
		case 386:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION72)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 387:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION72)+ResourceUtil.srcStr(StringKeysTip.TIP_UPDATE_NODE);
		case 388:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION73)+ResultString.QUERY_FAILED;
		case 389:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION76)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 390:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION76)+ResourceUtil.srcStr(StringKeysTip.TIP_BEFORE_AFTER_SITE);
		case 391:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION75)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 392:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION75)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_1TO1);
		case 393:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION75)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PROTECT_TUNNEL);
		case 394:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION76)+ResourceUtil.srcStr(StringKeysTip.TIP_LABEL_OCCUPY);
		case 395:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION76)+ResourceUtil.srcStr(StringKeysTip.TIP_QOSISNOTENOUGH);
		case 396:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION76)+ResourceUtil.srcStr(StringKeysTip.TIP_LABEL_REPEAT);
		case 397:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION76)+ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE);
		case 398:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION76)+ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE);
		case 399:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION77)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_REMOVE);
		case 400:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION77)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_REMOVE);
		case 401:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION77)+ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE);
		case 402:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION78)+ResourceUtil.srcStr(StringKeysTip.TIP_8_LAG);
		case 403:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION78)+ResourceUtil.srcStr(StringKeysTip.TIP_4_LAG);
		case 404:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION88)+ResourceUtil.srcStr(StringKeysTip.TIP_LAG_USE);
		case 405:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION88)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE);
		case 406:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION78)+ResourceUtil.srcStr(StringKeysTip.TIP_MUST_PORT_MEMBERS);
		case 407:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION78)+ResourceUtil.srcStr(StringKeysTip.TIP_SAME_SPEED_PROT);
		case 408:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION79)+ResourceUtil.srcStr(StringKeysTip.TIP_MUST_PORT_MEMBERS);
		case 409:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION80)+ResourceUtil.srcStr(StringKeysTip.TIP_MEPIDANDMIPIDISREPEAT);
		case 410:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION81)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 411:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION82)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 412:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION82)+ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_TYPE);
		case 413:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION83)+ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_TYPE);
		case 414:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION83)+ResourceUtil.srcStr(StringKeysLbl.LBL_NEOUT);
		case 415:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION83)+ResourceUtil.srcStr(StringKeysLbl.LBL_NEOUT);
		case 416:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION84)+ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_TYPE);
		case 417:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION83)+ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_TYPE);
		case 418:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION82)+ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE);
		case 419:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION82)+ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_TYPE);
		case 420:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION85)+ResourceUtil.srcStr(StringKeysTip.TIP_NO_PW);
		case 421:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION86)+ResourceUtil.srcStr(StringKeysTip.LBL_NO_PORT);
		case 422:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION87)+ResourceUtil.srcStr(StringKeysTip.TIP_NO_JOB_PATH);
		case 423:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_AZ_PROTECT_BEFORE);
		case 424:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_AZ_CONFIG_BEFORE);
		case 425:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_SITE_LOCK);
		case 426:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_PORT_UNUNITED);
		case 427:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_JOB_PROJECT_PORT);
		case 428:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_PORTNULL);
		case 429:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_LABEL_REPEAT);
		case 430:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_LABEL_OCCUPY);
		case 431:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_AZ_CONFIG_BEFORE);
		case 432:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_PLEASE_WORKROUTER);
		case 433:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_PLEASE_PROTROUTER);
		case 434:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_QOS_FILL);
		case 435:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_NO_SNCP);
		case 436:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL);
		case 437:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_LABEL_NUMBER);
		case 438:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_NO_ROUTE);
		case 439:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_NO_JOB_PATH);
		case 440:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_AZ_PROTECT_BEFORE);
		case 441:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_NO_PROTECT_PATH);
		case 442:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE);
		case 443:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_PLEASE_WORKROUTER);
		case 444:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_PLEASE_PROTROUTER);
		case 445:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_QOS_FILL);
		case 446:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 447:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_QOSISNOTENOUGH);
		case 448:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL);
		case 449:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_LABEL_ISUSED);
		case 450:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_PORT_OCCUPY);
		case 451:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_PLEASE_SELECT_A);
		case 452:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_PLEASE_SELECT_Z);
		case 453:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_LABEL_FILL);
		case 454:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_QOSISNOTENOUGH_E1);
		case 455:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_NOTLOOP);
		case 456:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_NOTLOOP_WAY);
		case 457:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_ROOT_PORT);
		case 458:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_BRANCH);
		case 459:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_BRANCH_PORT);
		case 460:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PW_ERROR);
		case 461:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_RONGPATH);
		case 462:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_PWANDACNOMATE);
		case 463:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_MACEMPTY);
		case 464:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_IPEMPTY);
		case 465:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_MACERROR);
		case 466:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_IPERROR);
		case 467:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_PWANDACNOMATE_OTHER);
		case 468:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_AC);
		case 469:
			return ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION);//未配置QOS
		case 470:
			return ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION1);//文件正在使用中
		case 471:
			return ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION2);//创建表头失败
		case 472:
			return ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION3);//请选择一条数据再进行操作
		case 473:
			return ResourceUtil.srcStr(StringKeysBtn.BTN_CONSISTENCE)+":"+ResourceUtil.srcStr(StringKeysTip.TIP_ONLY_ONLINE);//只有在线网元才能进行一致性检测
		case 474:
			return ResourceUtil.srcStr(StringKeysTip.TIP_SYSALARM);//当前告警同步成功
		case 475:
			return ResourceUtil.srcStr(StringKeysTip.TIP_ALARSHIELD);//"告警反转成功";
		case 476:
			return ResourceUtil.srcStr(StringKeysTip.TIP_ALARSHIELDS);//"告警屏蔽设置成功";
		case 477:
			return ResourceUtil.srcStr(StringKeysTip.TIP_NETWORKCESACTIVE);//"网络侧CES激活成功";
		case 478:
			return ResourceUtil.srcStr(StringKeysTip.TIP_NETWORKCESDOACTIVE);//"网络侧CES去激活成功";
		case 479:
			return ResourceUtil.srcStr(StringKeysTip.TIP_LOOPPROTECT);//"环网保护设置成功";
		case 480:
			return ResourceUtil.srcStr(StringKeysTip.TIP_INSERTQIQ);//"QIQ新建成功";
		case 481:
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATEQIQ);//"QIQ修改成功";
		case 482:
			return ResourceUtil.srcStr(StringKeysTip.TIP_NETELAEACTIVE);//"网络侧ELAN业务激活成功";
		case 483:
			return ResourceUtil.srcStr(StringKeysTip.TIP_NETELAEDOACTICE);//"网络侧ELAN业务去激活成功";
		case 484:
			return ResourceUtil.srcStr(StringKeysTip.TIP_NETELAEDOELINEACTICE);//"网络侧Eline业务激活成功";
		case 485:
			return ResourceUtil.srcStr(StringKeysTip.TIP_NETELAEELINEDOACTICE);//"网络侧Eline业务去激活成功";
		case 486:
			return ResourceUtil.srcStr(StringKeysTip.TIP_NETELAEDOETREEEACTICE);//"网络侧ETree业务激活成功";
		case 487:
			return ResourceUtil.srcStr(StringKeysTip.TIP_NETELAEETREEDOACTICE);//"网络侧ETree业务去激活成功";
		case 488:
			return ResourceUtil.srcStr(StringKeysTip.TIP_DELETEQIQ);//"删除QIQ业务成功";
		case 489:
			return ResourceUtil.srcStr(StringKeysTip.TIP_INSERTTNP);//"新建TNP通道成功";
		case 490:
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATETTNP);//"修改TNP通道成功";
		case 491:
			return ResourceUtil.srcStr(StringKeysTip.TIP_DELETEACL);//"删除ACL配置成功";
		case 492:
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATEALLCONFIG);//"修改全局配置块成功";
		case 493:
			return ResourceUtil.srcStr(StringKeysTip.TIP_DELETEBLACK);//"删除黑白名单配置成功";
		case 494:
			return ResourceUtil.srcStr(StringKeysTip.TIP_INSERTBLACK);//"新建黑白名单配置成功";
		case 495:
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATEBLACK);//"修改黑白名单配置成功";
		case 496:
			return ResourceUtil.srcStr(StringKeysTip.TIP_SINGCESACTIVE);//"单站侧CES激活配置成功";
		case 497:
			return ResourceUtil.srcStr(StringKeysTip.TIP_SINGCESDOACTIVE);//"单站侧CES去激活配置成功";
		case 498:
			return ResourceUtil.srcStr(StringKeysTip.TIP_DELETEDISC);//"删除丢弃流配置成功";
		case 499:
			return ResourceUtil.srcStr(StringKeysTip.TIP_INSERTDISC);//"新建丢弃流配置成功";
		case 500:
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATEDUAL);//"修改双规保护配置块成功";
		case 501:
			return ResourceUtil.srcStr(StringKeysTip.TIP_INSERTDUAL);//"新建双规保护配置块成功";
		case 502:
			return ResourceUtil.srcStr(StringKeysTip.TIP_SINGELANACTIVE);//"单站侧Elan业务激活成功";
		case 503:
			return ResourceUtil.srcStr(StringKeysTip.TIP_SINGELANDOACTIVE);//"单站侧Elan业务去激活成功";
		case 504:
			return ResourceUtil.srcStr(StringKeysTip.TIP_SINGELINEACTIVE);//"单站侧Eline业务激活成功";
		case 505:
			return ResourceUtil.srcStr(StringKeysTip.TIP_SINGELINEDOACTIVE);//"单站侧Eline业务去激活成功";
		case 506:
			return ResourceUtil.srcStr(StringKeysTip.TIP_PORTQOS);//"端口配置QoS业务配置成功";
		case 507:
			return ResourceUtil.srcStr(StringKeysTip.TIP_ETHLOOPINSERT);//"以太网环回新建成功";
		case 508:
			return ResourceUtil.srcStr(StringKeysTip.TIP_ETHLOOPUPDATE);//"以太网环回修改成功";
		case 509:
			return ResourceUtil.srcStr(StringKeysTip.TIP_DELETEETHSERVICE);//"以太网二层业务配置块删除";
		case 510:
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATEETHSERVICE);//"以太网环回同步成功";
		case 511:
			return ResourceUtil.srcStr(StringKeysTip.TIP_SINGETREEACTIVE);//"单站侧Etree激活配置成功";
		case 512:
			return ResourceUtil.srcStr(StringKeysTip.TIP_SINGETREEDOACTIVE);//"单站侧Etree去激活配置成功";
		case 513:
			return ResourceUtil.srcStr(StringKeysTip.TIP_INSERTGORUP);//"新建静态组播管理配置成功";
		case 514:
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATEGORUP);//"修改静态组播管理配置成功";
		case 515:
			return ResourceUtil.srcStr(StringKeysTip.TIP_DELETEGORUP);//"删除静态组播管理配置成功";
		case 516:
			return ResourceUtil.srcStr(StringKeysTip.TIP_CREATEL2CP);//"新建L2CP配置成功";
		case 517:
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATEL2CP);//"修改L2CP配置成功";
		case 518:
			return ResourceUtil.srcStr(StringKeysTip.TIP_DELETEMAC);//"删除MAC学习数目限制管理";
		case 519:
			return ResourceUtil.srcStr(StringKeysTip.TIP_DELETEBACLMANAGER);//"删除黑名单MAC管理";
		case 520:
			return ResourceUtil.srcStr(StringKeysTip.TIP_CREATEBACLMANAGER);//"新建黑名单MAC管理";
		case 521:
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATEBACLMANAGER);//"修改黑名单MAC管理";
		case 522:
			return ResourceUtil.srcStr(StringKeysTip.TIP_CREATEMAC);//"新建MAC学习数目限制管理";
		case 523:
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATEMAC);//"修改MAC学习数目限制管理";
		case 524:
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATEE1);//"修改E1端口";
		case 525:
			return ResourceUtil.srcStr(StringKeysTip.TIP_E1SYC);//"E1端口同步";
		case 526:
			return ResourceUtil.srcStr(StringKeysTip.TIP_V35SYC);//"V35端口管理同步";
		case 527:
			return ResourceUtil.srcStr(StringKeysTip.TIP_V35UPDATE);//"V35端口管理修改";
		case 528:
			return ResourceUtil.srcStr(StringKeysTip.TIP_V35UPCREATE);//"V35端口管理新建";
		case 529:
			return ResourceUtil.srcStr(StringKeysTip.TIP_PERFORMILTUPDATE);//"性能门限修改";
		case 530:
			return ResourceUtil.srcStr(StringKeysTip.TIP_PERFORMILTSYC);//"性能门限同步";
		case 531:
			return ResourceUtil.srcStr(StringKeysTip.TIP_PHBTOPRI)+ResourceUtil.srcStr(StringKeysMenu.MENU_SYNCHRO);
		case 532:
			return ResourceUtil.srcStr(StringKeysTip.TIP_PHBTOPRI)+ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE);
		case 533:
			return ResourceUtil.srcStr(StringKeysTip.TIP_RESTMANAGER)+ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE);
		case 534: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_fanMANAGER)+ResourceUtil.srcStr(StringKeysMenu.MENU_SYNCHRO);
		case 535: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_fanMANAGER)+ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE);
		case 536: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_E1STATE);//"E1仿真状态查询";
		case 537: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_LINKOAM);//"接入链路oam查询";
		case 538: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_OAMMEP);//"接入链路以太网OAM对端MEP信息";
		case 539: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ETHOAM);//"接入链路以太网oam状态查询 ";
		case 540: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_OAMPING);//"以太网OAM ping状态信息查询 ";
		case 541: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_OAMTRACE);//"以太网OAM TRACE状态信息";
		case 542: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PORTSTATE);//"端口状态信息查询";
		case 543: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PWSTATE);//"PW状态信息查询";
		case 544: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNELSTATE);//"TUNNEL状态信息查询";
		case 545: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SERVICESTATE);//"业务状态信息查询";
		case 546: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_LOOPPROTECTSTATE);//"环保护状态信息查询";
		case 547: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_STATICSTATE)+ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE);
		case 548: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TDMLOOP)+ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE);
		case 549: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNELPROTECT)+ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE);
		case 550: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNELPROTECT)+ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE);
		case 551: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PWOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE);
		case 552: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PWOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE);
		case 553: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DEGMENTOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE);
		case 554: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DEGMENTOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE);
		case 555: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNELOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE);
		case 556: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNELOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE);
		case 557: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_NE);//""网元解锁";
		case 558: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_MIOINTORSERVICE);//""配置监控服务器数据";
		case 559: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DB);//""配置监控数据库数据";
		case 560: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPGRADENE);//"设置升级";
		case 561: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SEGMENTOAM)+ResourceUtil.srcStr(StringKeysMenu.MENU_SYNCHRO);//"单站段层OAM同步";
		case 562: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ETHOAMSING)+ResourceUtil.srcStr(StringKeysMenu.MENU_SYNCHRO);//"单站以太网OAM同步";
		case 563: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ETHLINKOAM)+ResourceUtil.srcStr(StringKeysMenu.MENU_SYNCHRO);//"单站以太网链路OAM同步";
		case 564: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ETHLINKOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE);//"单站以太网链路OAM删除";
		case 565: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ETHOAMSING)+ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE);//"单站以太网OAM删除";
		case 566: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SEGMENTOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE);//"单站段层主动OAM删除";
		case 567: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PWOAMSING)+ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE);//"单站pw主动OAM删除";
		case 568: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNELOAMSING)+ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE);//"单站tunnel主动OAM删除";
		case 569: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SEGMENTTESTOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE);//"单站段层按需OAM新建";
		case 570: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SEGMENTTESTOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE);//"单站段层按需OAM修改";
		case 571: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SEGMENTTESTOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE);//"单站段层按需OAM删除";
		case 572: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PWTESTOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE);//"单站PW按需OAM新建";
		case 573: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PWTESTOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE);//"单站PW按需OAM修改";
		case 574: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PWTESTOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE);//"单站PW按需OAM删除";
		case 575: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNELTESTOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE);//"单站TUNNEL按需OAM新建";
		case 576: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNELTESTOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE);//"单站TUNNEL按需OAM修改";
		case 577: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNELTESTOAM)+ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE);//"单站TUNNEL按需OAM删除";
		case 578: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_EXCEEDPWNUMBER);//创建单站Etree业务通道PW超过最大值64
		case 579: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_EXCEEDACNUMBER);//创建单站Etree业务通道PW超过最大值64
		case 580: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);//名称已存在
		case 581: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_LABEL_FILL);//请配置标签
		case 582: 
			return ResourceUtil.srcStr(StringKeysPanel.PANEL_UPGRADE);//网元批量升级
		case 583: 
			return ResourceUtil.srcStr(StringKeysLbl.LBL_SET_TIME);//设置重启时间
		case 584: 
			return ResourceUtil.srcStr(StringKeysLbl.LBL_CANCLE_RBOOT_TIME);//取消定时重启
		case 585: 
			return ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE)+ResourceUtil.srcStr(StringKeysPanel.ETHSERVICE_TABLE);//创建以太网二层业务配置块
		case 586: 
			return ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE)+ResourceUtil.srcStr(StringKeysPanel.ETHSERVICE_TABLE);//修改以太网二层业务配置块
		case 587: 
			return ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE)+ResourceUtil.srcStr(StringKeysPanel.ETHSERVICE_TABLE);//删除以太网二层业务配置块
		case 588: 
			return ResourceUtil.srcStr(StringKeysMenu.MENU_SYNCHRO)+ResourceUtil.srcStr(StringKeysPanel.ETHSERVICE_TABLE);//同步以太网二层业务配置块
		case 589: 
			return ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE)+ResourceUtil.srcStr(StringKeysPanel.PANEL_ACL_MANAGE);//新建ACL业务配置块
		case 590: 
			return ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE)+ResourceUtil.srcStr(StringKeysPanel.PANEL_ACL_MANAGE);//修改ACL业务配置块
		case 591: 
			return ResourceUtil.srcStr(StringKeysMenu.MENU_SYNCHRO)+ResourceUtil.srcStr(StringKeysPanel.PANEL_ACL_MANAGE);//同步ACL业务配置块
		case 592:
			return ResourceUtil.srcStr(StringKeysMenu.MENU_NNI_PORT_MOVED);//NNI端口迁移
		case 593:
			return ResourceUtil.srcStr(StringKeysMenu.MENU_UNI_PORT_MOVED);//UNI端口迁移
		case 594: 
			return ResourceUtil.srcStr(StringKeysMenu.MENU_SYNCHRO)+ResourceUtil.srcStr(StringKeysLbl.LBL_GLOBAL_CONFIG);//同步全局配置置块
		case 595: 
			return ResourceUtil.srcStr(StringKeysMenu.MENU_SYNCHRO)+ResourceUtil.srcStr(StringKeysPanel.PANEL_STATIC_MANAGE);//同步静态单
		case 596: 
			return ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE)+ResourceUtil.srcStr(StringKeysPanel.PANEL_STATIC_MANAGE);//删除静态单
		case 597: 
			return ResourceUtil.srcStr(StringKeysTitle.TIT_MODIFY_STATIC);//修改静态单
		case 598: 
			return ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_STATIC);//新建静态单
		case 599: 
			return ResourceUtil.srcStr(StringKeysOperaType.BTN_EXPMAPPINGSYNCHRO);
		case 600: 
			return ResourceUtil.srcStr(StringKeysMenu.MENU_SYNCHRO)+ResourceUtil.srcStr(StringKeysTip.TIP_PHBTOPRI);
		case 601: 
			return ResourceUtil.srcStr(StringKeysTitle.TIP_PHBMAPPINGF);
		case 602: 
			return ResourceUtil.srcStr(StringKeysTitle.TIP_UPDATETIMESYN);//修改时间同步配置
		case 603: 
			return ResourceUtil.srcStr(StringKeysTitle.TIP_INSERTTIMESYN);//新建时间同步配置
		case 604: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SEGMENTNOT_DELETEONLINE);//删除段时判断是否为在线托管网元	
		case 605: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNELNOT_DELETEONLINE);//删除tunnel时判断是否为在线托管网元	
		case 606: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PWNOT_DELETEONLINE);//删除pw时判断是否为在线托管网元	
		case 607: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ELINENOT_DELETEONLINE);//删除eline时判断是否为在线托管网元	
		case 608: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ETREENOT_DELETEONLINE);//删除ETREE时判断是否为在线托管网元	
		case 609: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ELANNOT_DELETEONLINE);//删除ELAN时判断是否为在线托管网元	
		case 610: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DUALNOT_DELETEONLINE);//删除dual时判断是否为在线托管网元	
		case 611: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_CESNOT_DELETEONLINE);//删除ces时判断是否为在线托管网元	
		case 612: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_QINQNOT_DELETEONLINE);//删除ces时判断是否为在线托管网元
		case 613: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_LOOPNOT_DELETEONLINE);//删除环网保护时判断是否为在线托管网元	
		case 614: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCDUALNOT_DELETEONLINE);//删除单侧双规时判断是否为在线托管网元	
		case 615: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ETHSERVICENOT_DELETEONLINE);//删除单侧以太网二层业务配置块时判断是否为在线托管网元	
		case 616: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SECONDMACNOT_DELETEONLINE);//删除单侧二层静态MAC时判断是否为在线托管网元	
		case 617: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ACLNOT_DELETEONLINE);//删除单侧acl时判断是否为在线托管网元	
		case 618: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_STATICUNINOT_DELETEONLINE);//删除单侧静态单播时判断是否为在线托管网元	
		case 619: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCELANNOT_DELETEONLINE);//删除单侧elan时判断是否为在线托管网元	
		case 620: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCETREENOT_DELETEONLINE);//删除单侧etree时判断是否为在线托管网元	
		case 621: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCELINENOT_DELETEONLINE);//删除单侧ELINE时判断是否为在线托管网元	
		case 622: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCCESENOT_DELETEONLINE);//删除单侧CES时判断是否为在线托管网元	
		case 623: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCPWENOT_DELETEONLINE);//删除单侧PW时判断是否为在线托管网元	
		case 624: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCTUNNELENOT_DELETEONLINE);//删除单侧TUNNEL时判断是否为在线托管网元	
		case 625: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCLAGNOT_DELETEONLINE);//删除单侧lag时判断是否为在线托管网元	
		case 626: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCPTPPORTNOT_DELETEONLINE);//删除时间同步PTP端口时判断是否为在线托管网元	
		case 627: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCDISCARDFLOWNOT_DELETEONLINE);//删除丢弃流时判断是否为在线托管网元	
		case 628: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCETHOAMNOT_DELETEONLINE);//删除以太网OAM时判断是否为在线托管网元	
		case 629: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCETHLINKOAMNOT_DELETEONLINE);//删除以太网链路OAM时判断是否为在线托管网元	
		case 630: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCSEGMENTOAMNOT_DELETEONLINE);//删除段层OAM时判断是否为在线托管网元	
		case 631: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCPWOAMNOT_DELETEONLINE);//删除PW层OAM时判断是否为在线托管网元	
		case 632: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCTUNNELOAMNOT_DELETEONLINE);//删除TUNNEL层OAM时判断是否为在线托管网元	
		case 633: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCAXSEGMENTOAMNOT_DELETEONLINE);//删除按需OAM段层时判断是否为在线托管网元	
		case 634: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCAXTUNNELOAMNOT_DELETEONLINE);//删除按需OAM TUNNEL层时判断是否为在线托管网元	
		case 635: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DCAXPWOAMNOT_DELETEONLINE);//删除按需OAM PW层时判断是否为在线托管网元	
		case 636: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_WLAXTUNNELOAMNOT_DELETEONLINE);//删除网络侧按需OAM TUNNEL层时判断是否为在线托管网元	
		case 637: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_WLAXPWOAMNOT_DELETEONLINE);//删除网络侧按需OAM pw层时判断是否为在线托管网元	
		case 638: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_WLAXSEGMENTOAMNOT_DELETEONLINE);//删除网络侧按需OAM 段层时判断是否为在线托管网元	
		case 639: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TNPDELETE_DELETEONLINE);//删除网络侧按需OAM 段层时判断是否为在线托管网元	
		case 640: 
			return ResourceUtil.srcStr(StringKeysTitle.TIT_ACTIVE_DUAL);//激活双归业务
		case 641: 
			return ResourceUtil.srcStr(StringKeysTitle.TIT_UNACTIVE_DUAL);//去激活双归业务
		case 642: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SYS_SECONDMAC);//同步二层静态MAC	
		case 643: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SYS_LOOPPROTECT);//同步	环网保护
		case 644: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SYS_TIMESYN);//同步时间同步
		case 645: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_BLACKANDWHITEDELETE_DELETEONLINE);//黑白MAC地址
		case 646: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_MACMANAGEDELETE_DELETEONLINE);//黑MAC地址
		case 647: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SYS_L2CP);//L2CP同步
		case 648: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SYS_MACMANAGE);//mac黑名单同步
		case 649: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SYS_BLACKWHITEMAC);//mac黑白名单同步
		case 650: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ALARMSOUND_OPEN);//开启告警声音
		case 651: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ALARMSOUND_CLOSE);//关闭告警声音
		case 652: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SELF_MANAGE);//个性化设置
		case 653: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_HAND_LOCK);//屏幕锁定
		case 654: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATE_SETNAMERULE);//屏幕锁定
		case 655: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SITE_WORKIP);//网元下发
		case 656: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_CANCLE_BATCHUPGRADE);//取消批量升级
		case 657: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SYNC_PM);//性能门限同步
		case 658: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATE_ETHLINKOAM);//修改以太网链路OAM
		case 659: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_INSERT_ETHLINKOAM);//新建以太网链路OAM
		case 660: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_LAG_PORTSTATUS);//端口聚合状态查询
		case 661: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PWPROTECT_STATUS);//PW保护状态查询
		case 662: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_LSPPROTECT_STATUS);//LSP保护状态查询
		case 663: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_CLOCK_STATUS);//时钟状态变长状态查询		
		case 664: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_CLOCKBASIC_STATUS);//时钟状态基本信息查询	
		case 665: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_DOWN_SERVISON);//单站查询摘要
		case 666: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_BATCHDOWN_SERVISON);//批量升级查询摘要
		case 667: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_CANCLE_UPGRADE);//取消升级
		case 668: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_SYNC_CLOCK);//时钟频率同步
		case 669: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ROATE_CLOCK);//时钟频率倒换
		case 670: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_CLOCK_COMFIRM);//时钟频率保存
		case 671: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_EMS_PORT);//一致性检测保存网管数据port
		case 672: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_EMS_TUNNEL);//一致性检测保存网管数据TUNNEL
		case 673: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_EMS_PW);//一致性检测保存网管数据PW
		case 674: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_EMS_ELINE);//一致性检测保存网管数据ELINE
		case 675: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_EMS_ETREE);//一致性检测保存网管数据ETREE
		case 676: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_EMS_ELAN);//一致性检测保存网管数据ELAN
		case 677: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_EMS_CES);//一致性检测保存网管数据CES
		case 678: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PORT_EMS);//一致性检测保存设备数据port
		case 679: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNEL_EMS);//一致性检测保存设备数据TUNNEL
		case 680: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PW_EMS);//一致性检测保存设备数据PW
		case 681: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ELINE_EMS);//一致性检测保存设备数据ELINE
		case 682: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ETREE_EMS);//一致性检测保存设备数据ETREE
		case 683: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_ELAN_EMS);//一致性检测保存设备数据ELAN
		case 684: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_CES_EMS);//一致性检测保存设备数据CES
		case 685: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_NE_UPLOAD);//网元上下载
		case 686: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_NE_DOWNLOAD);//网元上下载
		case 687: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TESTOAM_SEGMENTUPDATE);//段层按需oam修改
		case 688: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TESTOAM_TUNNELUPDATE);//TUNNEL层按需oam修改
		case 689: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TESTOAM_PWUPDATE);//PW层按需oam修改
		case 690: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TESTOAM_SEGMENTDELETE);//段层按需oam删除
		case 691: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TESTOAM_TUNNELDELETE);//TUNNEL层按需oam删除
		case 692: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TESTOAM_PWDELETE);//PW层按需oam删除
		case 693: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNEL_QOS);//TUNNELQOS修改
		case 694: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PW_QOS);//PW层按需oam删除
		case 695: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TNP_DELETE);//TNP删除
		case 696: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNEL_ACTIVE);//TUNNEL激活
		case 697: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_TUNNEL_UNACTIVE);//tunnel去激活
		case 698: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_PW_ACTIVE);//PW激活
		case 699:  
			return ResourceUtil.srcStr(StringKeysTip.TIP_PW_UNACTIVE);//PW去激活
		case 700:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_ADD_LSP_NODE);//扩容
		case 701:
			return ResourceUtil.srcStr(StringKeysTitle.TIT_DELETE_LSP_NODE);//缩容
	
		case 702:  
			return ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_CCC);//删除CCC
		case 703:  
			return ResourceUtil.srcStr(StringKeysTip.TIP_SYNC_CCC);//同步CCC
		case 704:  
			return ResourceUtil.srcStr(StringKeysTip.TIP_ACTIVE_CCC);//激活CCC
		case 705:  
			return ResourceUtil.srcStr(StringKeysTip.TIP_UNACTIVE_CCC);//激活CCC
		case 706:  
			return ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_NE);//查询某网元属性
		case 707:  
			return ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_CCC1);//查询某网元属性
		case 708: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_CCC_EMS);//一致性检测保存设备数据CCC	
		case 709: 
			return ResourceUtil.srcStr(StringKeysTip.TIP_EMS_CCC);//一致性检测保存网管数据CCC	
		case 710:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATIONC1)+ResourceUtil.srcStr(StringKeysTip.TIP_DATAISNULL);
		case 711:
			return  ResourceUtil.srcStr(StringKeysTip.TIP_OPERATIONC2)+ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
		case 712:  
			return ResourceUtil.srcStr(StringKeysTip.TIP_INSERT_CCC);//新建CCC
		case 713:  
			return ResourceUtil.srcStr(StringKeysTip.TIP_UPDATE_CCC);//修改CCC
		case 714:  
			return ResourceUtil.srcStr(StringKeysTip.TIP_TELNET_MANAGE);//TELNET设置
		case 715: 
			return ResourceUtil.srcStr(StringKeysTitle.TIT_PTPPORTSTATUS);//ptp端口状态
		case 716: 
			return ResourceUtil.srcStr(StringKeysTitle.TIT_PTPPORTBASICSTATUS);//ptp端口状态
			
		
		default :
			return "";
		}
	}
}
