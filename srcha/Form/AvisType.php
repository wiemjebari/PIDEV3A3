<?php

namespace App\Form;

use App\Entity\Avis; 
use phpDocumentor\Reflection\Types\False_;
use PhpParser\Node\Scalar\MagicConst\File;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use blackknight467\StarRatingBundle\Form\RatingType;

class AvisType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options )
    {
        $builder
            ->add('note',RatingType::class,[
                'stars' => 5,
            ])
            ;



    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Avis::class,
        ]);
    }
}
