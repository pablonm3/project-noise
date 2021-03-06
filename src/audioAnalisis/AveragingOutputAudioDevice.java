package audioAnalisis;
/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * 2009 http://www.streamhead.com
 */

import java.util.LinkedList;
import java.util.Queue;


import javazoom.jl.decoder.JavaLayerException;


/**
 * @author Peter Backx
 */
public class AveragingOutputAudioDevice extends BaseOutputAudioDevice {
    private int averageLength = 1024; // number of samples over which the average is calculated
    private Queue<Short> instantBuffer = new LinkedList<Short>();

    public AveragingOutputAudioDevice(SampleProcessor processor) {
        super(processor);
    }

    @Override
    protected void outputImpl(short[] samples, int offs, int len) throws JavaLayerException {
        for(int i=0; i<len; i++)
            instantBuffer.offer(samples[i]);

        while(instantBuffer.size()>averageLength*channels)
        {
            long[] average = new long[channels];
            for(int i=0; i<averageLength; i++)
                for(int c=0; c<channels; c++)
                    average[c] += instantBuffer.poll();

            for(int c=0; c<channels; c++)
                average[c] = average[c]/len;

            if(processor != null)
                processor.process(average);
        }
    }
}
