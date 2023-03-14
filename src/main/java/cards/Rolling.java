package cards;

import actions.TwintailSpiralStrikeAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ReinforcedBodyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import demoMod.ERIRIMod;
import pathes.AbstractCardEnum;

public class Rolling extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Rolling");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/Rolling.png";
    private static final int COST = -1;
    private static final int BLOCK_AMT = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    public static final String ID = "Rolling";


    public Rolling() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.ERIRI_COLOR, AbstractCard.CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = BLOCK_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ReinforcedBodyAction(p, this.block, this.freeToPlayOnce, this.energyOnUse));
        if(this.energyOnUse > 0){
            this.addToBot(new ApplyPowerAction(p, p, new BlurPower(p, 1), 1));
        }

    }

    @Override
    public AbstractCard makeCopy() {
        return new Rolling();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}
