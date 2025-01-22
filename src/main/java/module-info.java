import se.uu.ub.cora.diva.extended.divaoutput.urn.UrnExtendedFunctionalityFactory;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityFactory;

module se.uu.ub.cora.diva {
	requires se.uu.ub.cora.spider;
	requires se.uu.ub.cora.data;
	requires se.uu.ub.cora.storage;

	provides ExtendedFunctionalityFactory with UrnExtendedFunctionalityFactory;

}