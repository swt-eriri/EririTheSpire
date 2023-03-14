package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import pathes.AbstractCardEnum;

public class PatrioticForm extends CustomCard{
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Patriotic Form");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/PatrioticForm.png";
    private static final int COST = 1;
    private static final int MAGICNUMBER = 4;
    private static final int UPGRADE_MAGIC_NUMBER = 2;
    public static final String ID = "PatrioticForm";

    public PatrioticForm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.ERIRI_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = MAGICNUMBER;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCreature target = AbstractDungeon.player;
        this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new PlatedArmorPower(target, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }
    }

    public AbstractCard makeCopy() {
        return new PatrioticForm();
    }

}
