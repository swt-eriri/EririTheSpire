package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import powers.ViewingPower;

public class Viewing extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Viewing");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/Viewing.png";
    private static final int COST = 1;
    public int amount = 2;
    private static final int MAGICNUMBER = 2;
    public static final String ID = "Viewing";
    public Viewing() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.ERIRI_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = MAGICNUMBER;
        this.magicNumber = this.baseMagicNumber;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ViewingPower(p, this.magicNumber, this.amount), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Viewing();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.amount++;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }

    }
}
