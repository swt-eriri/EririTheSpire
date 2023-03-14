package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;

public class Disconnect extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Disconnect");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/Disconnect.png";
    private static final int COST = -2;
    public static final String ID = "Disconnect";

    public Disconnect() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    public void onChoseThisOption() {
        this.addToBot(new SkipEnemiesTurnAction());
        this.addToBot(new PressEndTurnButtonAction());
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }

    public AbstractCard makeCopy() {
        return new Disconnect();
    }
}
