/*
 * Copyright (c) 2019 AlexIIL
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package alexiil.mc.lib.attributes.fluid.impl;

import alexiil.mc.lib.attributes.Simulation;
import alexiil.mc.lib.attributes.fluid.FluidExtractable;
import alexiil.mc.lib.attributes.fluid.FluidInsertable;
import alexiil.mc.lib.attributes.fluid.FluidTransferable;
import alexiil.mc.lib.attributes.fluid.FluidVolumeUtil;
import alexiil.mc.lib.attributes.fluid.filter.ConstantFluidFilter;
import alexiil.mc.lib.attributes.fluid.filter.FluidFilter;
import alexiil.mc.lib.attributes.fluid.volume.FluidVolume;
import alexiil.mc.lib.attributes.misc.NullVariant;

/** An {@link FluidTransferable} that never returns any items from
 * {@link #attemptExtraction(FluidFilter, int, Simulation)}, nor accepts any items in
 * {@link #attemptInsertion(FluidVolume, Simulation)}. */
public enum EmptyFluidTransferable implements FluidTransferable, NullVariant {
    /** An {@link FluidTransferable} that should be treated as equal to null in all circumstances - that is any checks
     * that depend on an object being transferable should be considered FALSE for this instance. */
    NULL,

    /** An {@link FluidTransferable} that informs callers that it will interact with a nearby {@link FluidTransferable},
     * {@link FluidExtractable}, or {@link FluidInsertable} but doesn't expose any other item based attributes. */
    CONTROLLER;

    private final String str = "EmptyFluidTransferable." + name();

    @Override
    public FluidVolume attemptInsertion(FluidVolume fluid, Simulation simulation) {
        return fluid;
    }

    @Override
    public FluidFilter getInsertionFilter() {
        return ConstantFluidFilter.NOTHING;
    }

    @Override
    public FluidVolume attemptExtraction(FluidFilter filter, int maxAmount, Simulation simulation) {
        return FluidVolumeUtil.EMPTY;
    }

    @Override
    public FluidInsertable getPureInsertable() {
        return this == NULL ? RejectingFluidInsertable.NULL : RejectingFluidInsertable.EXTRACTOR;
    }

    @Override
    public FluidExtractable getPureExtractable() {
        return this == NULL ? EmptyFluidExtractable.NULL : EmptyFluidExtractable.SUPPLIER;
    }

    @Override
    public String toString() {
        return str;
    }
}
