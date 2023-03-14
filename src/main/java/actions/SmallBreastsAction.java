package actions;

import basemod.patches.com.megacrit.cardcrawl.screens.select.GridCardSelectScreen.CustomActionHooks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class SmallBreastsAction extends AbstractGameAction {
    private boolean freeToPlayOnce = false;
    private AbstractPlayer p;
    private int energyOnUse = -1;
    private boolean upgraded;

    public SmallBreastsAction(AbstractPlayer p, int energyOnUse, boolean upgraded, boolean freeToPlayOnce) {
        this.p = p;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.upgraded = upgraded;
        this.freeToPlayOnce = freeToPlayOnce;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse - 1;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (this.upgraded) {
            ++effect;
        }

        if (effect > 0) {
            for(int i = 0; i < effect; ++i) {
                this.addToBot(new ApplyPowerAction(p, p, new BufferPower(p, 1), 1));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
