package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import demoMod.ERIRIMod;
import pathes.AbstractCardEnum;

public class FlusteredAndExasperated extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("FlusteredAndExasperated");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/FlusteredAndExasperated.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 7;
    private static final int MAGICNUMBER = 3;
    private static final int UPGRADE_MAGIC_NUMBER = 1;
    public static final String ID = "FlusteredAndExasperated";

    public FlusteredAndExasperated() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.ERIRI_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = MAGICNUMBER;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            this.addToBot(new DiscardAction(p, p, 1, true));
            this.addToBot(new DrawCardAction(p, 1));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }
    }

    public AbstractCard makeCopy() {
        return new FlusteredAndExasperated();
    }
}
