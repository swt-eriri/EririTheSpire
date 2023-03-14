package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

public class Innocence extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Innocence");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/Innocence.png";
    private static final int COST = 0;
    private static final int MAGICNUMBER = 2;
    public static final String ID = "Innocence";
    public Innocence() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.ERIRI_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = MAGICNUMBER;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!this.upgraded){
            if(p.drawPile.size() % 2 == 0 && p.discardPile.size() % 2 == 0){
                this.addToBot(new GainEnergyAction(magicNumber));
            }
        }
        else {
            if(p.discardPile.size() % 2 == 0){
                this.addToBot(new GainEnergyAction(magicNumber));
            }
        }

    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if(!this.upgraded){
            if(AbstractDungeon.player.drawPile.size() % 2 == 0 && AbstractDungeon.player.discardPile.size() % 2 == 0){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
        }
        else {
            if(AbstractDungeon.player.discardPile.size() % 2 == 0){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    public AbstractCard makeCopy() {
        return new Innocence();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }
}
