package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.Iterator;

public class RestPaintingAction extends AbstractGameAction {
    private AbstractPlayer p;
    private DamageInfo info;

    public RestPaintingAction(AbstractCreature target, DamageInfo info) {
        this.p = AbstractDungeon.player;
        this.actionType = ActionType.DAMAGE;
        this.info = info;
        this.setValues(target, info);
        this.duration = 0.1F;
    }

    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HORIZONTAL));
            int strAmt = 0;
            if (this.p.hasPower("Vigor")) {
                strAmt = this.p.getPower("Vigor").amount;
            }
            this.target.damage(this.info);
            if(strAmt > 0){
                this.addToBot(new ApplyPowerAction(this.p, this.p, new VigorPower(this.p, strAmt), strAmt));
            }

        }
        this.tickDuration();
    }
}
