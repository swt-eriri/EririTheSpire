package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import powers.CoolPower;
import powers.FoodiePower;

import java.util.Iterator;

public class FoodieForm extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("FoodieForm");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/FoodieForm.png";
    private static final int COST = 1;
    private static final int MAGICNUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER = 1;
    public static final String ID = "FoodieForm";

    public FoodieForm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.ERIRI_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = MAGICNUMBER;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new FoodiePower(p, this.magicNumber)));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }
    }

    public AbstractCard makeCopy() {
        return new FoodieForm();
    }
}
