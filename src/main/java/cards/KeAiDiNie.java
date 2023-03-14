package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import powers.CutePower;


public class KeAiDiNie extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("KeAiDiNie");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/KeAiDiNie.png";
    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;
    public static final String ID = "KeAiDiNie";

    public KeAiDiNie() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.ERIRI_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new CutePower(p, magicNumber), magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.upgradeBaseCost(UPGRADE_COST);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new KeAiDiNie();
    }
}
