package demoMod;

import basemod.BaseMod;
import basemod.interfaces.*;
import cards.*;
import characters.ERIRI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import events.HomoExhibition;
import events.SummerComicMarket;
import events.WinterComicMarket;
import pathes.AbstractCardEnum;
import pathes.ERIRIMODClassEnum;
import relics.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

@SpireInitializer
public class ERIRIMod implements RelicGetSubscriber, PostPowerApplySubscriber, PostExhaustSubscriber, PostBattleSubscriber, PostDungeonInitializeSubscriber, EditCharactersSubscriber, PostInitializeSubscriber, EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber, PostDrawSubscriber, PostEnergyRechargeSubscriber {
    private static final String MOD_BADGE = "img/UI_ERIRI/badge.png";
    //攻击、技能、能力牌的图片(512)
    private static final String ATTACK_CC = "img/512/bg_attack_ERIRI_s.png";
    private static final String SKILL_CC = "img/512/bg_skill_ERIRI_s.png";
    private static final String POWER_CC = "img/512/bg_power_ERIRI_s.png";
    private static final String ENERGY_ORB_CC = "img/512/ERIRIOrb.png";
    //攻击、技能、能力牌的图片(1024)
    private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_ERIRI.png";
    private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_ERIRI.png";
    private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_ERIRI.png";
    private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/ERIRIOrb.png";
    public static final String CARD_ENERGY_ORB = "img/UI_ERIRI/energyOrb.png";
    //选英雄界面的角色图标、选英雄时的背景图片
    private static final String MY_CHARACTER_BUTTON = "img/charSelect/button.png";
    private static final String MARISA_PORTRAIT = "img/charSelect/background.png";
    public static final Color GOLDEN = CardHelper.getColor(255, 243, 47);
    private ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();
    public static ArrayList<AbstractCard> recyclecards = new ArrayList<>();
    @SpireEnum
    public static AbstractCard.CardTags TWINTAILS;

    public ERIRIMod() {
        //构造方法，初始化各种参数
        BaseMod.subscribe((ISubscriber)this);
        BaseMod.addColor(AbstractCardEnum.ERIRI_COLOR, GOLDEN, GOLDEN, GOLDEN, GOLDEN, GOLDEN, GOLDEN, GOLDEN, ATTACK_CC, SKILL_CC, POWER_CC, ENERGY_ORB_CC, ATTACK_CC_PORTRAIT, SKILL_CC_PORTRAIT,POWER_CC_PORTRAIT, ENERGY_ORB_CC_PORTRAIT, CARD_ENERGY_ORB);
    }

    @Override
    public void receiveEditCharacters() {
        //添加角色到MOD中
        BaseMod.addCharacter((AbstractPlayer)new ERIRI("ERIRI"), MY_CHARACTER_BUTTON, MARISA_PORTRAIT, ERIRIMODClassEnum.ERIRI_CLASS);
    }
    //初始化整个MOD,一定不能删
    public static void initialize() {
        new ERIRIMod();
    }

    @Override
    public void receiveEditCards() {
        //将卡牌批量添加
        loadCardsToAdd();
        Iterator<AbstractCard> var1 = this.cardsToAdd.iterator();
        while (var1.hasNext()) {
            AbstractCard card = var1.next();
            BaseMod.addCard(card);
        }
    }

    @Override
    public void receivePostExhaust(AbstractCard c) {}

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower pow, AbstractCreature target, AbstractCreature owner) {

    }


    @Override
    public void receivePowersModified() {}


    @Override
    public void receivePostDungeonInitialize() {}


    @Override
    public void receivePostDraw(AbstractCard arg0) {}

    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }


    @Override
    public void receiveEditKeywords() {
        String keywordsPath = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            keywordsPath = "localization/ERIRI_Mod_keywords-zh.json";
        }

        Gson gson = new Gson();
        ERIRIMod.Keywords keywords = gson.fromJson(loadJson(keywordsPath), ERIRIMod.Keywords.class);
        Keyword[] var1 = keywords.keywords;
        int var1len = var1.length;

        for(int var2 = 0; var2 < var1len; ++var2) {
            Keyword key = var1[var2];
            BaseMod.addKeyword(key.NAMES, key.DESCRIPTION);
        }

    }

    @Override
    public void receiveEditStrings() {
        //读取遗物，卡牌，能力，药水，事件的JSON文本

        String relic="", card="", power="", ui="", potion="", event="", keyword="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            card = "localization/ERIRI_Mod_cards-zh.json";
            relic = "localization/ERIRI_Mod_relics-zh.json";
            power = "localization/ERIRI_Mod_powers-zh.json";
            ui = "localization/ERIRI_Mod_ui-zh.json";
            event = "localization/ERIRI_Mod_events-zh.json";
        } else {
            //其他语言配置的JSON
        }

        String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        String uiStrings = Gdx.files.internal(ui).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(UIStrings.class, uiStrings);
//     String potionStrings = Gdx.files.internal(potion).readString(String.valueOf(StandardCharsets.UTF_8));
//     BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
        String eventStrings = Gdx.files.internal(event).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
    }

    private void loadCardsToAdd() {
        this.cardsToAdd.clear();
        this.cardsToAdd.add(new Strike_ERIRI());
        this.cardsToAdd.add(new Defend_ERIRI());
        this.cardsToAdd.add(new TwintailStrike());
        this.cardsToAdd.add(new Run());
        this.cardsToAdd.add(new Jinlile());
        this.cardsToAdd.add(new ThatsEnough());
        this.cardsToAdd.add(new ItTakesTwo());
        this.cardsToAdd.add(new Rewrite());
        this.cardsToAdd.add(new TwintailSmash());
        this.cardsToAdd.add(new Shy());
        this.cardsToAdd.add(new KeAiDiNie());
        this.cardsToAdd.add(new SmallBreasts());
        this.cardsToAdd.add(new Damn());
        this.cardsToAdd.add(new DamnIt());
        this.cardsToAdd.add(new PatrioticForm());
        this.cardsToAdd.add(new Emmmmm());
        this.cardsToAdd.add(new TurnBack());
        this.cardsToAdd.add(new WhoAreYou());
        this.cardsToAdd.add(new DreamTime());
        this.cardsToAdd.add(new TongueOut());
        this.cardsToAdd.add(new Impatient());
        this.cardsToAdd.add(new Meditation());
        this.cardsToAdd.add(new Hardworking());
        this.cardsToAdd.add(new TwintailSpiralStrike());
        this.cardsToAdd.add(new TwintailFlashStrike());
        this.cardsToAdd.add(new Desert());
        this.cardsToAdd.add(new ToBeFirst());
        this.cardsToAdd.add(new Sketching());
        this.cardsToAdd.add(new Copy());
        this.cardsToAdd.add(new Viewing());
        this.cardsToAdd.add(new Hcomic());
        this.cardsToAdd.add(new ComicBook());
        this.cardsToAdd.add(new FreeDraw());
        this.cardsToAdd.add(new BigSmallWitch());
        this.cardsToAdd.add(new HolyLight());
        this.cardsToAdd.add(new Disconnect());
        this.cardsToAdd.add(new Lesbian());
        this.cardsToAdd.add(new WithMegumi());
        this.cardsToAdd.add(new Peaceful());
        this.cardsToAdd.add(new Innocence());
        this.cardsToAdd.add(new TwintailThrash());
        this.cardsToAdd.add(new ChangeProfession());
        this.cardsToAdd.add(new DarkThoughts());
        this.cardsToAdd.add(new Patience());
        this.cardsToAdd.add(new JointSanctions());
        this.cardsToAdd.add(new LivePointing());
        this.cardsToAdd.add(new Confidence());
        this.cardsToAdd.add(new DrinkingTea());
        this.cardsToAdd.add(new TheRoadWillContinue());
        this.cardsToAdd.add(new ChangingClothes());
        this.cardsToAdd.add(new DiscardTheCase());
        this.cardsToAdd.add(new ImproveAbility());
        this.cardsToAdd.add(new TwintailSacrificeAttack());
        this.cardsToAdd.add(new DestroyTheEvidence());
        this.cardsToAdd.add(new FullOfVigor());
        this.cardsToAdd.add(new OnePunchGirl());
        this.cardsToAdd.add(new Rolling());
        this.cardsToAdd.add(new HeartBroken());
        this.cardsToAdd.add(new VigorStrike());
        this.cardsToAdd.add(new RestPainting());
        this.cardsToAdd.add(new DefendBroken());
        this.cardsToAdd.add(new PaintingDemonForm());
        this.cardsToAdd.add(new GG());
        this.cardsToAdd.add(new TwintailCutStrike());
        this.cardsToAdd.add(new CoolForm());
        this.cardsToAdd.add(new BurstLiver());
        this.cardsToAdd.add(new FoodieForm());
        this.cardsToAdd.add(new StormInhalation());
        this.cardsToAdd.add(new BlackFace());
        this.cardsToAdd.add(new Alone());
        this.cardsToAdd.add(new TopArt());
        this.cardsToAdd.add(new IdentifiedAsFake());
        this.cardsToAdd.add(new BlondeHairLeague());
        this.cardsToAdd.add(new FlusteredAndExasperated());
        this.cardsToAdd.add(new MaidForm());
        this.cardsToAdd.add(new YouAre());
        this.cardsToAdd.add(new BigBreasts());
        this.cardsToAdd.add(new RetainTheCase());
        this.cardsToAdd.add(new SkillfulPainting());
        this.cardsToAdd.add(new ActStupid());
    }
    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new Twintails(),AbstractCardEnum.ERIRI_COLOR);
        BaseMod.addRelicToCustomPool(new HairBand(),AbstractCardEnum.ERIRI_COLOR);
        BaseMod.addRelicToCustomPool(new ContactLenses(),AbstractCardEnum.ERIRI_COLOR);
        BaseMod.addRelicToCustomPool(new DraftingPaper(),AbstractCardEnum.ERIRI_COLOR);
        BaseMod.addRelicToCustomPool(new PaintingCollection(),AbstractCardEnum.ERIRI_COLOR);
        BaseMod.addRelicToCustomPool(new BlondeHairTwintails(),AbstractCardEnum.ERIRI_COLOR);
    }

    @Override
    public void receiveRelicGet(AbstractRelic relic) {
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {

    }

    @Override
    public void receivePostBattle(AbstractRoom r) {

    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addEvent("HomoExhibition", HomoExhibition.class);
        BaseMod.addEvent("WinterComicMarket", WinterComicMarket.class,"Exordium");
        BaseMod.addEvent("SummerComicMarket", SummerComicMarket.class,"TheCity");
    }

    @Override
    public void receivePostEnergyRecharge() {
        Iterator<AbstractCard> var1 = recyclecards.iterator();

        while (var1.hasNext()) {
            AbstractCard c = var1.next();
            AbstractCard card = c.makeStatEquivalentCopy();
            AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, false, true, true));
        }
        recyclecards.clear();
    }

    class Keywords {
        Keyword[] keywords;
    }
}

