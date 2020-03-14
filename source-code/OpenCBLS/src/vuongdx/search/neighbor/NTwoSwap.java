package vuongdx.search.neighbor;

import java.util.ArrayList;
import java.util.HashMap;

import localsearch.model.IConstraint;
import localsearch.model.IFunction;
import localsearch.model.VarIntLS;
import vuongdx.search.INeighborLS;

public class NTwoSwap implements INeighborLS {
	
	public int idx1;
	public int idx2;
	
	public NTwoSwap() {
		
	}
	
	public NTwoSwap(int idx1, int idx2) {
		this.idx1 = idx1;
		this.idx2 = idx2;
	}

	@Override
	public int movePropagate(HashMap<String, VarIntLS[]> dVar) {
		VarIntLS[] mVar;
		try {
			mVar = dVar.get("main");
			VarIntLS var1 = mVar[this.idx1];
			VarIntLS var2 = mVar[this.idx2];
			int val1 = var1.getValue();
			int val2 = var2.getValue();
			var1.setValuePropagate(val2);
			var2.setValuePropagate(val1);
			return 0;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int getMoveDelta(IFunction f, HashMap<String, VarIntLS[]> dVar) {
		VarIntLS[] mVar;
		try {
			mVar = dVar.get("main");
			VarIntLS var1 = mVar[this.idx1];
			VarIntLS var2 = mVar[this.idx2];
			return f.getSwapDelta(var1, var2);
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int getMoveDelta(IConstraint cs, HashMap<String, VarIntLS[]> dVar) {
		VarIntLS[] mVar;
		try {
			mVar = dVar.get("main");
			VarIntLS var1 = mVar[this.idx1];
			VarIntLS var2 = mVar[this.idx2];
			return cs.getSwapDelta(var1, var2);
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public INeighborLS[] listNeighbor(IConstraint cs, IFunction[] f, HashMap<String, VarIntLS[]> dVar) {
		VarIntLS[] mVar;
		try {
			mVar = dVar.get("main");
			ArrayList<NTwoSwap> tmpNeighborList = new ArrayList<NTwoSwap>();
			for (int i = 0; i < mVar.length; i++) {
				for (int j = i + 1; j < mVar.length; j++) {
					tmpNeighborList.add(new NTwoSwap(i, j));
				}
			}
			NTwoSwap[] neighborList = new NTwoSwap[tmpNeighborList.size()];
			for (int i = 0; i < tmpNeighborList.size(); i++) {
				neighborList[i] = tmpNeighborList.get(i);
			}
			return neighborList;
		} catch (Exception e) {
			return null;
		}
	}

}
