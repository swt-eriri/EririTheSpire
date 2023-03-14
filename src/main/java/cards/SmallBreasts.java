package cards;

import actions.SmallBreastsAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

public class SmallBreasts extends CustomCard{
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Small Breasts");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/SmallBreasts.png";
    public static final String ID = "SmallBreasts";

    public SmallBreasts() {
        super(ID, NAME, IMG_PATH, -1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.ERIRI_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SmallBreastsAction(p, this.energyOnUse, this.upgraded, this.freeToPlayOnce));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new SmallBreasts();
    }
}
