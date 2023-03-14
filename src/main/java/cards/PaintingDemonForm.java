package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import powers.ConfidencePower;
import powers.PaintingDemonPower;

public class PaintingDemonForm extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("PaintingDemonForm");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/PaintingDemonForm.png";
    private static final int COST = 3;
    public static final String ID = "PaintingDemonForm";

    public PaintingDemonForm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.ERIRI_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;
        this.cardsToPreview = new Rewrite();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PaintingDemonPower(p, 1), 1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.isEthereal = false;
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new PaintingDemonForm();
    }
}