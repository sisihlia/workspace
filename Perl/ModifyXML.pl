use strict;
use warnings;
use Data::Dumper;

use XML::LibXML;

sub getInput{
	#my $argus = @ARGV;
	#my $argu = 'ENBEquipment/CpriRadioEquipment/attributes/booleanSpare00';
	#my $val = $_[1];
	#my $argu = $_[0];
	my @split = split("/", $_[0]);
	
	print "@split .\n";
	return @split;

}

sub modifyValue {

	my @split = @_;
	my $val = $ARGV[1];
	my $path = join("//u:", @split[0..($#split-2)]);
	
	my $xml = "test.xml";

	my $dom = XML::LibXML->load_xml( location => $xml);
	my $context = XML::LibXML::XPathContext->new( $dom->documentElement() );
	$context->registerNs( 'w' => 'urn:ietf:params:xml:ns:netconf:base:1.0' );
	$context->registerNs( 'u' => 'http://alcatel-lucent.com/lte/enb');
	
	for my $node ( $context->findnodes("//u:$path") ) {
	my ($mh)   = $context->findnodes("u:$split[$#split-1]", $node);
    my $size  = $context->findnodes("u:$split[$#split-1]", $node) ->size;
 	
  	#print "size $size";
  	if ($size != 1) {next;}
  	$mh ->removeChildNodes();
    $mh->appendText($val);
	
   print "mh = $mh";
	}
   $dom->toFile('test.xml');
}

my @array = getInput($ARGV[0]);
modifyValue(@array, $ARGV[1]);

