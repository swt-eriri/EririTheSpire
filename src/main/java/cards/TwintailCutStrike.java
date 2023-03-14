package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import demoMod.ERIRIMod;
import pathes.AbstractCardEnum;

import java.util.Iterator;

public class TwintailCutStrike extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("TwintailCutStrike");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_ERIRI/TwintailCutStrike.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 9;
    private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = "TwintailCutStrike";

    public TwintailCutStrike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.ERIRI_COLOR, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = ATTACK_DMG;
        this.tags.add(CardTags.STRIKE);
        this.tags.add(ERIRIMod.TWINTAILS);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if(AbstractDungeon.player.drawPile.size() % 2 == 0){
            Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            this.addToBot(new SFXAction("ATTACK_HEAVY"));
            this.addToBot(new VFXAction(p, new CleaveEffect(), 0.0F));
            while(var3.hasNext()) {
                AbstractMonster mo = (AbstractMonster)var3.next();
                this.addToBot(new DamageAction(mo, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.drawPile.size() % 2 == 0) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

    public AbstractCard makeCopy() {
        return new TwintailCutStrike();
    }
}
