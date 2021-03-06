/*
 * Copyright (c) 2019 AlexIIL
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package alexiil.mc.lib.attributes.fluid.filter;

import alexiil.mc.lib.attributes.fluid.volume.FluidKey;

/** The default implementation for {@link FluidFilter#negate()} */
public final class InvertedFluidFilter implements ReadableFluidFilter {

    public final FluidFilter delegate;

    public InvertedFluidFilter(FluidFilter delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean matches(FluidKey fluidKey) {
        return !delegate.matches(fluidKey);
    }

    @Override
    public FluidFilter negate() {
        return delegate;
    }
}
