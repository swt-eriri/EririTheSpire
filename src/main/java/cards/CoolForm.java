package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BarricadePower;
import pathes.AbstractCardEnum;
import powers.ConfidencePower;
import powers.CoolPower;

import java.util.Iterator;

public class CoolForm extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("CoolForm");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/CoolForm.png";
    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    public static final String ID = "CoolForm";

    public CoolForm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.ERIRI_COLOR, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean powerExists = false;
        Iterator var4 = p.powers.iterator();

        while(var4.hasNext()) {
            AbstractPower pow = (AbstractPower)var4.next();
            if (pow.ID.equals("CoolPower")) {
                powerExists = true;
                break;
            }
        }

        if (!powerExists) {
            this.addToBot(new ApplyPowerAction(p, p, new CoolPower(p)));
        }

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
        }
    }

    public AbstractCard makeCopy() {
        return new CoolForm();
    }
}

