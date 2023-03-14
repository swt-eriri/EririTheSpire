package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

public class RetainTheCase extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("RetainTheCase");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/RetainTheCase.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 6;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int MAGICNUMBER = 1;
    public static final String ID = "RetainTheCase";
    public RetainTheCase() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.ERIRI_COLOR, CardRarity.COMMON, CardTarget.NONE);
        this.baseBlock = BLOCK_AMT;
        this.baseMagicNumber = MAGICNUMBER;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        if (!p.hand.isEmpty()) {
            this.addToBot(new RetainCardsAction(p, this.magicNumber));
        }

    }

    public AbstractCard makeCopy() {
        return new RetainTheCase();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
        }

    }
}
