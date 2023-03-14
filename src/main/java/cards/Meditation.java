package cards;
import actions.MeditationAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pathes.AbstractCardEnum;

public class Meditation extends CustomCard{
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Meditation");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/Meditation.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 4;
    private static final int MAGICNUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER = 1;
    public static final String ID = "Meditation";
    public Meditation() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.ERIRI_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = MAGICNUMBER;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = BLOCK_AMT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
            this.addToBot(new DrawCardAction(this.magicNumber, new MeditationAction(this.block)));

        }

    public AbstractCard makeCopy() {
        return new Meditation();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }

    }
}
