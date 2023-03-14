package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import pathes.AbstractCardEnum;
import powers.PaintingDemonPower;
import powers.YouArePower;

public class YouAre extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("YouAre");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/YouAre.png";
    private static final int COST = 2;
    private static final int MAGICNUMBER = 3;
    private static final int UPGRADE_MAGIC_NUMBER = -1;
    public static final String ID = "YouAre";

    public YouAre() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.ERIRI_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = MAGICNUMBER;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new BigBreasts();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -this.magicNumber), -this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new YouArePower(p, 1), 1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }
    }

    public AbstractCard makeCopy() {
        return new YouAre();
    }
}