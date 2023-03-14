package cards;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

public class Defend_ERIRI extends CustomCard
{
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_ERIRI");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    public static final String ID = "Defend_ERIRI";
    public static final String IMG_PATH = "img/cards_ERIRI/Defend.png";

    public Defend_ERIRI() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.ERIRI_COLOR, CardRarity.BASIC, CardTarget.SELF);
        this.tags.add(CardTags.STARTER_DEFEND);
        this.baseBlock = BLOCK_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Defend_ERIRI();
    }

    @Override
    public boolean isDefend() {
        return true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}