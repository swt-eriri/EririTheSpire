package cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import pathes.AbstractCardEnum;

public class Desert extends CustomCard{
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Desert");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/Desert.png";
    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER = 1;
    public static final String ID = "Desert";


    public Desert() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.ERIRI_COLOR, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.baseMagicNumber = MAGIC_NUMBER;
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void onRetained() {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));
        this.upgradeBlock(this.magicNumber);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Desert();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }
    }
}
