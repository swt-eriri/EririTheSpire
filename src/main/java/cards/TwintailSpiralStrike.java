package cards;
import actions.TwintailSpiralStrikeAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import demoMod.ERIRIMod;
import pathes.AbstractCardEnum;


public class TwintailSpiralStrike extends CustomCard{
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Twintail Spiral Strike");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/TwintailSpiralStrike.png";
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = "TwintailSpiralStrike";


    public TwintailSpiralStrike() {
        super(ID, NAME, IMG_PATH, -1, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCardEnum.ERIRI_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
        this.baseDamage = ATTACK_DMG;
        this.isMultiDamage = true;
        this.tags.add(ERIRIMod.TWINTAILS);
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new TwintailSpiralStrikeAction(p, this.multiDamage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }

    @Override
    public AbstractCard makeCopy() {
        return new TwintailSpiralStrike();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
