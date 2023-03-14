package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import powers.ComicBookPower;


public class ComicBook extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("ComicBook");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/ComicBook.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 3;
    private static final int UPGRADE_PLUS_BLOCK = 1;
    public static final String ID = "ComicBook";
    public ComicBook() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.ERIRI_COLOR, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE);
        this.baseBlock = BLOCK_AMT;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ComicBookPower(p, this.block), this.block));
    }

    public AbstractCard makeCopy() {
        return new ComicBook();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
        }

    }
}
