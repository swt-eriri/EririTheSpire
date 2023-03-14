package cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import cards.Rewrite;

public class ItTakesTwo extends CustomCard{

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("It Takes Two");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/ItTakesTwo.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 10;
    private static final int UPGRADE_PLUS_DMG = 4;
    public static final String ID = "ItTakesTwo";

    public ItTakesTwo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.ERIRI_COLOR, CardRarity.RARE, CardTarget.ENEMY);
        this.cardsToPreview = new Rewrite();
        this.baseDamage = ATTACK_DMG;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_LIGHT));
        this.addToBot(new MakeTempCardInHandAction(new Rewrite(), 1));
    }

    @Override

    public AbstractCard makeCopy() {
        return new ItTakesTwo();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}