package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import powers.HcomicPower;

public class Hcomic extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Hcomic");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/Hcomic.png";
    private static final int COST = 1;
    private static final int MAGICNUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER = 1;
    public static final String ID = "Hcomic";
    public Hcomic() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.ERIRI_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = MAGICNUMBER;
        this.magicNumber = this.baseMagicNumber;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new HcomicPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Hcomic();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }

    }
}

