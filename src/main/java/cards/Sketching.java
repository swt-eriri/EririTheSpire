package cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import powers.SketchingPower;

public class Sketching extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Sketching");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/Sketching.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 6;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int MAGICNUMBER = 3;
    private static final int UPGRADE_MAGIC_NUMBER = 1;
    public static final String ID = "Sketching";
    public Sketching() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.ERIRI_COLOR, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = MAGICNUMBER;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = BLOCK_AMT;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SketchingPower(p, 1, this.magicNumber, this.block), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Sketching();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }

    }
}
