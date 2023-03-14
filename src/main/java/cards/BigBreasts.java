package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class BigBreasts extends CustomCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BigBreasts");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/BigBreasts.png";
    private static final int COST = 0;
    private static final int MAGICNUMBER = 10;
    private static final int UPGRADE_MAGIC_NUMBER = 4;
    public static final String ID = "BigBreasts";

    public BigBreasts() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.selfRetain = true;
        this.exhaust = true;
        this.baseMagicNumber = MAGICNUMBER;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.magicNumber), this.magicNumber));
    }

    @Override

    public AbstractCard makeCopy() {
        return new BigBreasts();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }
    }
}