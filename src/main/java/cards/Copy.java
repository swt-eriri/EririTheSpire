package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import powers.CopyPower;

public class Copy extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Copy");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/Copy.png";
    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 1;
    private static final int DAMAGE = 12;
    private static final int UPGRADE_DAMAGE_NUMBER = 4;
    public static final String ID = "Copy";
    public Copy() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.ERIRI_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseMagicNumber = MAGIC_NUMBER;
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new CopyPower(m, this.magicNumber, this.damage), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Copy();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DAMAGE_NUMBER);
        }

    }
}
