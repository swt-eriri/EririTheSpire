package cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pathes.AbstractCardEnum;
public class ToBeFirst extends CustomCard{
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("To Be First");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/ToBeFirst.png";
    private static final int COST = 0;
    private static final int MAGICNUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER = 1;
    public static final String ID = "ToBeFirst";
    public ToBeFirst() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.ERIRI_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = MAGICNUMBER;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
        this.exhaust = true;
        this.isInnate = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, this.magicNumber));
    }

    public void onRetained() {
        this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
    }

    public AbstractCard makeCopy() {
        return new ToBeFirst();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }

    }
}
